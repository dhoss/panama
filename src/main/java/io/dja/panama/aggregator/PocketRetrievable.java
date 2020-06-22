package io.dja.panama.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PocketRetrievable implements Retrievable<ImmutablePocketRequest, ImmutablePocketResponse> {
    
    @Value("${pocket.baseUrl}")
    private String baseUrl;
    
    @Autowired
    private WebClient webclient;
    
    private MediaType contentType = MediaType.APPLICATION_JSON;
    
    @Autowired
    public PocketRetrievable(WebClient webClient) {
        this.webclient = webClient;
    }
    
    public ImmutablePocketResponse get(ImmutablePocketRequest request) {
        System.out.println("**** BASE URL IN RETRIEVABLE "+ baseUrl);
        return this.webclient.post()
                .uri(baseUrl + "/get")
                .contentType(contentType)
                .body(Mono.just(request), ImmutablePocketRequest.class)
                .retrieve()
                .bodyToMono(ImmutablePocketResponse.class)
                .block();
    }
}