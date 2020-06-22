package io.dja.panama.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    
    Logger logger = LoggerFactory.getLogger(Config.class);
    
    @Value("${pocket.baseUrl}")
    private String baseUrl;
    
    @Value("${pocket.consumerKey}")
    private String consumerKey;
    
    @Value("${pocket.accessToken")
    private String accessToken;
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }
    
    @Bean
    public String baseUrl() {
        return this.baseUrl;
    }
    
    @Bean
    public WebClient webClient(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(cfg -> cfg
                                .defaultCodecs()
                                .jackson2JsonDecoder(
                                new Jackson2JsonDecoder(this.objectMapper())))
                        .build())
                .filter(logRequest())
                .build();
    }
    
    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("******Request: {} {} {}",
                    clientRequest.method(), clientRequest.url(), clientRequest.body());
            clientRequest.headers()
                    .forEach(
                            (name, values) ->
                                    values.forEach(
                                            value -> logger.info(
                                                    "*****Headers {}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}