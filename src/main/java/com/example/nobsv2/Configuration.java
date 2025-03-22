package com.example.nobsv2;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    // Gives us access to rest template throughout the application
    public RestTemplate restTemplate() {
        // Configure rest template if needed
        return new RestTemplate();
    }
}
