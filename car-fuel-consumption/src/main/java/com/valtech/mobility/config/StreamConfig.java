package com.valtech.mobility.config;

import com.valtech.mobility.model.FuelConsumptionDetails;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.core.KafkaTemplate;


@EnableKafkaStreams
public class StreamConfig {

    @Value(value = "${kafka.fuel.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${kafka.fuel.average.topic}")
    private String fuelAverageTopic;
    @Value("${kafka.fuel.consumer.group-id.notification}")
    private String groupId;

    private KafkaTemplate<String, Double> kafkaTemplate;

    @Bean
    public KStream<String, FuelConsumptionDetails> fuelConsumptionStream(StreamsBuilder streamsBuilder) {
        KStream<String, FuelConsumptionDetails> fuelStream = streamsBuilder.stream("car-fuel-consumption");

        KTable<String, double[]> averageFuelConsumption = fuelStream
                .groupBy((key, value) -> value.getCarId())
                .aggregate(
                        () -> new double[]{0.0, 0}, // [totalFuel, count]
                        (key, value, aggregate) -> {
                            double totalFuel = aggregate[0] + value.getFuelUsed();
                            int count = (int) aggregate[1] + 1;
                            return new double[]{totalFuel, count};
                        },
                        Materialized.with(Serdes.String(), new AvgFuelSerde())
                );

        averageFuelConsumption.toStream().foreach((carId, totalCount) -> {
            if (totalCount[1] != 0) {
                double average = totalCount[0] / totalCount[1];
                System.out.printf("Average fuel consumed by car %s: %.2f liters%n", carId, average);
                kafkaTemplate.send(fuelAverageTopic, carId, average);
            } else {
                System.out.printf("No fuel data available for car %s yet.%n", carId);
            }
        });

        return fuelStream;
    }
}