package com.valtech.mobility.service;

import com.valtech.mobility.model.LocationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service("NotificationService")
public class CreateLocationConsumer {

    private static final Logger log = LoggerFactory.getLogger(CreateLocationConsumer.class);

    @KafkaListener(topics = "${kafka.location.topic}", containerFactory="NotificationContainerFactory")
    public void createLocationListener(@Payload LocationDetail locationDetail, Acknowledgment ack) {
        log.info("Notification service received location {} ", locationDetail.toString());
        ack.acknowledge();
    }
}
