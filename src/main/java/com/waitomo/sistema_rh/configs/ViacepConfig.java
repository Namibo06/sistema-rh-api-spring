package com.waitomo.sistema_rh.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ViacepConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
