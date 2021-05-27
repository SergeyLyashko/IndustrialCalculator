package database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import viewcomponents.common.DataReceiver;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DetailsDAO();
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
