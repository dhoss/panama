package io.dja.panama.aggregator;

import io.dja.panama.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PocketRetrievable implements Retrievable<PocketRequest, PocketResponse> {
    
    Logger logger = LoggerFactory.getLogger(Config.class);
    
    @Value("${pocket.baseUrl}")
    private String baseUrl;
    
    @Autowired
    private WebClient webclient;
    
    private MediaType contentType = MediaType.APPLICATION_JSON;
    
    @Autowired
    public PocketRetrievable(WebClient webClient) {
        this.webclient = webClient;
    }
    
    public PocketResponse get(PocketRequest request) {
        return this.webclient.post()
                .uri(baseUrl + "/get")
                .contentType(contentType)
                .body(Mono.just(request), PocketRequest.class)
                .retrieve()
                .bodyToMono(PocketResponse.class)
                .block();
    }
}