package com.ecommerce.api_gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
