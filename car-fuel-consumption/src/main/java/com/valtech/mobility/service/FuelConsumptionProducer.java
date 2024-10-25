package com.valtech.mobility.service;

import com.valtech.mobility.model.FuelConsumptionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FuelConsumptionProducer {

    private static final Logger log = LoggerFactory.getLogger(FuelConsumptionProducer.class);

    private final KafkaTemplate<String, FuelConsumptionDetails> fuelConsumptionKafkaTemplate;

    private final String fuelConsumptionTopic;

    public FuelConsumptionProducer(KafkaTemplate<String, FuelConsumptionDetails> fuelConsumptionKafkaTemplate,
                                   @Value("${kafka.fuel.topic}") String fuelConsumptionTopic) {
        this.fuelConsumptionKafkaTemplate = fuelConsumptionKafkaTemplate;
        this.fuelConsumptionTopic = fuelConsumptionTopic;
    }

    public void sendFuelConsumptionEvent(FuelConsumptionDetails fuelConsumptionDetails) throws ExecutionException, InterruptedException {
        String key = fuelConsumptionDetails.getCarId();
        SendResult<String, FuelConsumptionDetails> sendResult = fuelConsumptionKafkaTemplate.send(fuelConsumptionTopic,key, fuelConsumptionDetails).get();
        log.info("Fuel Consumption {} event sent via Kafka", fuelConsumptionDetails);
        log.info(sendResult.toString());
    }
}
