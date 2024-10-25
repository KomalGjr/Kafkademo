package com.valtech.mobility;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableScheduling
public class SpringBootApplication
{

//    @Value("${kafka.fuel.topic}")
//    topicprivate String kafkaTopic;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name(kafkaTopic)
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
}
