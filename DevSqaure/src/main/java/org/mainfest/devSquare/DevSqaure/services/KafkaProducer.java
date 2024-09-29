package org.mainfest.devSquare.DevSqaure.services;

import org.apache.kafka.common.protocol.types.Field;
import org.mainfest.devSquare.DevSqaure.entities.CommentDTO;
import org.mainfest.devSquare.DevSqaure.entities.Querry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static  String TOPIC = "comments";
    private static  String TOPIC_2  = "mentions";

    public void send(String message){
        kafkaTemplate.send(TOPIC,message);
    }
    public void sendMention(String message){
        kafkaTemplate.send(TOPIC_2,message);
    }
}