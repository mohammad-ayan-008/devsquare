package org.mainfest.devSquare.DevSqaure.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MongoStreamService {


    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    private final   CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        MongoClient mongoClient = MongoClients.create(mongoUri);
        MongoDatabase database = mongoClient.getDatabase("DevCluster");
        MongoCollection<Document> collection = database.getCollection("user_collection");

        collection.watch().forEach((ChangeStreamDocument<Document> changeStreamDocument) -> {
           emitters.forEach(emitter->{
               try {
                   emitter.send(changeStreamDocument.getFullDocument().toJson());
               } catch (IOException e) {
                   emitter.completeWithError(e);
                   emitters.remove(emitter);
               }
           });
        });

    }

    public SseEmitter Subscribe(){
        SseEmitter sseEmitter = new SseEmitter();
        emitters.add(sseEmitter);
        sseEmitter.onCompletion(()->
            emitters.remove(sseEmitter));
            sseEmitter.onTimeout(()->
                emitters.remove(sseEmitter));

        return sseEmitter;
    }

}
