package com.valtech.mobility.service;

import com.valtech.mobility.model.MaintenanceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MaintenanceProducer {

    private static final Logger log = LoggerFactory.getLogger(MaintenanceProducer.class);

    private final KafkaTemplate<String, MaintenanceDetails> maintenanceKafkaTemplate;

    private final String maintenanceTopic;

    public MaintenanceProducer(KafkaTemplate<String, MaintenanceDetails> maintenanceKafkaTemplate,
                               @Value("${kafka.maintenance.topic}") String maintenanceTopic) {
        this.maintenanceKafkaTemplate = maintenanceKafkaTemplate;
        this.maintenanceTopic = maintenanceTopic;
    }

    public void sendMaintenanceEvent(MaintenanceDetails maintenanceDetails) throws ExecutionException, InterruptedException {
        SendResult<String, MaintenanceDetails> sendResult = maintenanceKafkaTemplate.send(maintenanceTopic, maintenanceDetails).get();
        log.info("Maintenance {} event sent via Kafka", maintenanceDetails);
        log.info(sendResult.toString());
    }
}
