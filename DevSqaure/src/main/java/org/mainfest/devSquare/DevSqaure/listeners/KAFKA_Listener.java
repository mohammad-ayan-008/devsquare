package org.mainfest.devSquare.DevSqaure.listeners;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.CopyOnWriteMap;
import org.mainfest.devSquare.DevSqaure.entities.Querry;
import org.mainfest.devSquare.DevSqaure.entities.payloads;
import org.mainfest.devSquare.DevSqaure.services.Notificationservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@Slf4j
public class KAFKA_Listener {
    private Logger logger = LoggerFactory.getLogger(KAFKA_Listener.class);

    private CopyOnWriteArrayList<SseEmitter> LIST_emiters= new CopyOnWriteArrayList<>();
    private final CopyOnWriteMap<String, List<SseEmitter>> emitters = new CopyOnWriteMap<>();
    @Autowired
    private Gson gson;
    @Autowired
    private Notificationservice notificationservice;

    @KafkaListener(topics = "comments", groupId = "data-change")
    public void  message(String message){
        logger.info(message);
        Querry querry = gson.fromJson(message, Querry.class);
        notificationservice.sendNotification(querry);
    }

    @KafkaListener(topics = "mentions",groupId = "data-change")
    public void getMentioned(String mention){
        logger.info("Mention"+mention);
        payloads pls = gson.fromJson(mention,payloads.class);
        notificationservice.sendMentionedNotification(pls);
    }


}
