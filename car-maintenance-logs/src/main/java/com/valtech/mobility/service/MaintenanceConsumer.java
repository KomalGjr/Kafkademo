package com.valtech.mobility.service;

import com.valtech.mobility.model.MaintenanceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service("NotificationService")
public class MaintenanceConsumer {

    private static final Logger log = LoggerFactory.getLogger(MaintenanceConsumer.class);

    @KafkaListener(topics = "${kafka.maintenance.topic}", containerFactory="NotificationContainerFactory")
    public void maintenanceListener(@Payload MaintenanceDetails maintenanceDetails, Acknowledgment ack) {
        log.info("Notification service received maintenance {} ", maintenanceDetails.toString());
        ack.acknowledge();
    }
}
