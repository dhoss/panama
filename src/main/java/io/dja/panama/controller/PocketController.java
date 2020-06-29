package io.dja.panama.controller;

import io.dja.panama.aggregator.pocket.ImmutablePocketRequest;
import io.dja.panama.aggregator.pocket.ImmutablePocketResponse;
import io.dja.panama.aggregator.pocket.PocketRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("/v1")
public class PocketController {
    Logger logger = LoggerFactory.getLogger(PocketController.class);
    @Value("${pocket.accessToken}")
    private String accessToken;
    
    @Value("${pocket.consumerKey}")
    private String consumerKey;
    
    @Autowired
    private final PocketRetriever pocketRetriever;
    
    @Autowired
    public PocketController(PocketRetriever pocketRetriever) {
        this.pocketRetriever = pocketRetriever;
    }
    
    @GetMapping(path = "/pocket")
    public ResponseEntity<ImmutablePocketResponse> retrievePocketSaves() {
        return new ResponseEntity<>(
                this.pocketRetriever.retrieve(
                        ImmutablePocketRequest
                                .builder()
                                .accessToken(accessToken)
                                .consumerKey(consumerKey)
                                .count(10)
                                .detailType("simple")
                                .build()),
                HttpStatus.OK);
    }
    
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}, Headers {}",
                ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex.getHeaders(), ex);
        return ResponseEntity
                .status(ex.getRawStatusCode())
                .body(ex.getResponseBodyAsString());
    }
}
