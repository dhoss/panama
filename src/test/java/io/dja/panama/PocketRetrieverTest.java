package io.dja.panama;

import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import io.dja.panama.aggregator.PocketRetriever;
import io.dja.panama.aggregator.Retrievable;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static io.dja.panama.fixtures.TestFixtures.buildPocketRequest;
import static io.dja.panama.fixtures.TestFixtures.buildPocketResponse;
import static io.dja.panama.fixtures.TestFixtures.readFileToString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PocketRetrieverTest {
    
    @Mock
    private Retrievable<ImmutablePocketRequest, ImmutablePocketResponse> retrievable;

    private PocketRetriever retriever;
    
    @Test
    public void testRetrieve() throws IOException {
        ImmutablePocketRequest pocketRequest =
                buildPocketRequest(readFileToString("pocket-request.json"));
        ImmutablePocketResponse pocketResponse =
                buildPocketResponse(readFileToString("pocket-response.json"));
        
        when(retrievable.get(pocketRequest)).thenReturn(pocketResponse);
        
        retriever = new PocketRetriever(retrievable);
        
        assertEquals(pocketResponse, retriever.retrieve(pocketRequest));
        verify(retrievable, times(1)).get(pocketRequest);
    }
}