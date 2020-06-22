package io.dja.panama;

import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import io.dja.panama.aggregator.PocketResponse;
import io.dja.panama.aggregator.PocketRetrievable;
import io.dja.panama.config.Config;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static io.dja.panama.fixtures.TestFixtures.buildPocketRequest;
import static io.dja.panama.fixtures.TestFixtures.buildPocketResponse;
import static io.dja.panama.fixtures.TestFixtures.readFileToString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PocketRetrievableTest {
   
    // TODO: bean this
    Logger logger = LoggerFactory.getLogger(PocketRetrievableTest.class);
    private WebClient webClient;
    private ImmutablePocketRequest POCKET_REQUEST;
    private ImmutablePocketResponse POCKET_RESPONSE;
    private String RAW_RESPONSE_JSON;
    
    private PocketRetrievable retrievable;
    
    @Before
    public void setup() throws IOException {
        retrievable = new PocketRetrievable(webClient);
        POCKET_REQUEST = buildPocketRequest(readFileToString("pocket-request.json"));
        POCKET_RESPONSE = buildPocketResponse(readFileToString("pocket-response.json"));
        RAW_RESPONSE_JSON = readFileToString("pocket-response.json");
        webClient = WebClient.builder().exchangeFunction(clientRequest ->
                Mono.just(ClientResponse.create(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(RAW_RESPONSE_JSON)
                        .build()))
                .build();
    }
    
    @Test
    public void testGet() {
   //     logger.debug("RESPONSE IN RETRIEABLE {}",POCKET_RESPONSE);
   //     logger.debug("REQ {}", POCKET_REQUEST);
   //     logger.debug("RAW {}", RAW_RESPONSE_JSON);
   //     PocketResponse r = retrievable.get(POCKET_REQUEST);
   //     assertEquals(POCKET_RESPONSE, r);
    }
}
