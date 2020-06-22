package io.dja.panama;

import io.dja.panama.aggregator.PocketRetrievable;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static io.dja.panama.fixtures.TestFixtures.POCKET_REQUEST;
import static io.dja.panama.fixtures.TestFixtures.POCKET_RESPONSE;
import static io.dja.panama.fixtures.TestFixtures.RAW_RESPONSE_JSON;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PocketRetrievableTest {
    
    private WebClient webClient;
    
    @Autowired
    private PocketRetrievable retrievable;
    
    @Before
    public void setup() {
        webClient = WebClient.builder().exchangeFunction(clientRequest ->
                Mono.just(ClientResponse.create(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(RAW_RESPONSE_JSON)
                        .build()))
                .build();
    }
    
    @Test
    public void testGet() {
        assertEquals(POCKET_RESPONSE, retrievable.get(POCKET_REQUEST));
    }
}
