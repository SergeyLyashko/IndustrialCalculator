package database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DataReceiverImpl();
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
