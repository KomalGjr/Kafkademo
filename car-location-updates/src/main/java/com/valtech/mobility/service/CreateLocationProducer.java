package com.valtech.mobility.service;

import com.valtech.mobility.model.LocationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CreateLocationProducer {

    private static final Logger log = LoggerFactory.getLogger(CreateLocationProducer.class);

    private final KafkaTemplate<String, LocationDetail> createLocationKafkaTemplate;

    private final String createLocationTopic;

    public CreateLocationProducer(KafkaTemplate<String, LocationDetail> createLocationKafkaTemplate,
                               @Value("${kafka.location.topic}") String createLocationTopic) {
        this.createLocationKafkaTemplate = createLocationKafkaTemplate;
        this.createLocationTopic = createLocationTopic;
    }

    public boolean sendCreateLocationEvent(LocationDetail locationDetail) throws ExecutionException, InterruptedException {
        SendResult<String, LocationDetail> sendResult = createLocationKafkaTemplate.send(createLocationTopic, locationDetail).get();
        log.info("Create Location {} event sent via Kafka", locationDetail);
        log.info(sendResult.toString());
        return true;
    }
}
