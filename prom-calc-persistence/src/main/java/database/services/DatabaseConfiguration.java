package database.services;

import database.MenuListProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public MenuListProducer dataReceiver(){
        return new MenuListProducerImpl();
    }

    @Bean
    public DatabaseExecutor executor(){
        return new DatabaseExecutor();
    }

    @Bean
    public DatabaseConnector databaseConnector(){
        return new DatabaseConnector();
    }
}
