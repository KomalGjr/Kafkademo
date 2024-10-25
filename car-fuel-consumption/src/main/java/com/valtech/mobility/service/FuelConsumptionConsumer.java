package com.valtech.mobility.service;

import com.valtech.mobility.model.FuelConsumptionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service("NotificationService")
public class FuelConsumptionConsumer {

    private static final Logger log = LoggerFactory.getLogger(FuelConsumptionConsumer.class);

    @KafkaListener(topics = "${kafka.fuel.topic}", containerFactory="NotificationContainerFactory")
    public void fuelConsumptionListener(@Payload FuelConsumptionDetails fuelConsumptionDetails, Acknowledgment ack) {
        log.info("Notification service received fuel Consuption {} ", fuelConsumptionDetails.toString());
        ack.acknowledge();
    }
}
