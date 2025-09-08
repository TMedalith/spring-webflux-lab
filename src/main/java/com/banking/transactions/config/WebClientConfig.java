package com.banking.transactions.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${account.service.url}")
    private String accountServiceUrl;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl(accountServiceUrl).build();
    }
}
