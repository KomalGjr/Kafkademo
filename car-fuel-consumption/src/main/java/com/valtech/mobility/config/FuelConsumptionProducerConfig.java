package com.valtech.mobility.config;

import com.valtech.mobility.model.FuelConsumptionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FuelConsumptionProducerConfig {

    @Value("${kafka.fuel.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, FuelConsumptionDetails> fuelConsumptionProducerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public KafkaTemplate<String, FuelConsumptionDetails> fuelConsumptionKafkaTemplate(){
        return new KafkaTemplate<>(fuelConsumptionProducerFactory());
    }
}
