package org.mainfest.devSquare.DevSqaure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Kafka_TopicConfs {
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("comments").build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name("mentions").build();
    }
}
