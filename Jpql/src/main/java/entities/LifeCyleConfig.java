package entities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCyleConfig {
    @Bean
    public  NetworkClient networkClient(){
        NetworkClient client = new NetworkClient();
        client.setUrl("www.fasgh.com");
        return client;
    }
}
