package io.dja.panama.aggregator.pocket;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
   
    @Test
    public void testGet() throws IOException {
        ImmutablePocketRequest pocketRequest =
                buildPocketRequest(readFileToString("pocket-request.json"));
        ImmutablePocketResponse pocketResponse =
                buildPocketResponse(readFileToString("pocket-response.json"));
        // I'd inline this but it whines about the IOException not being handled
        String rawJson = readFileToString("pocket-response.json");
    
        PocketRetrievable retrievable = new PocketRetrievable(
                WebClient
                        .builder()
                        .exchangeFunction(clientRequest ->
                                Mono.just(ClientResponse.create(HttpStatus.OK)
                                        .header("Content-Type", "application/json")
                                        .body(rawJson)
                                        .build()))
                        .build());
    
        assertEquals(pocketResponse, retrievable.get(pocketRequest));
    }
}