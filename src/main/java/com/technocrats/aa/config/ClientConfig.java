package com.technocrats.aa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.technocrats.aa.constants.AaConstants.*;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webClientAA() {
        return WebClient.builder()
                .baseUrl(AA_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("client_api_key", System.getProperty(CLIENT_API_KEY_KEY))
                .build();
    }

    @Bean
    public WebClient webClientDHE() {
        return WebClient.builder()
                .baseUrl(RAHASYA_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
