package org.mainfest.devSquare.DevSqaure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;

@SpringBootApplication
public class DevSqaureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevSqaureApplication.class, args);
	}



}
