package org.shoaib.dpdns.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

//import jakarta.annotation.PostConstruct;

@Configuration
public class MongoConfig {
	
	@Value("${mongo.connection.url}")
    private String mongoUrl;

    @Value("${mongo.database.name}")
    private String databaseName;
    
    MongoClient clients;
	
	 	@jakarta.annotation.PostConstruct
	    public void  mongoClient() {
	        ConnectionString connStr = new ConnectionString(mongoUrl);

	        MongoClientSettings settings = MongoClientSettings.builder()
	                .applyConnectionString(connStr)
	                .applyToSocketSettings(builder -> builder
	                        .connectTimeout(10, TimeUnit.SECONDS)
	                        .readTimeout(10, TimeUnit.SECONDS))
	                .applyToClusterSettings(builder -> builder
	                        .serverSelectionTimeout(10, TimeUnit.SECONDS))
	                .build();

	        clients =  MongoClients.create(settings);
	    }

	    
	    public MongoDatabase mongoDatabase() {
	       return clients.getDatabase(databaseName);
	    }

}
