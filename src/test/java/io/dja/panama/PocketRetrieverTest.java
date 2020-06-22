package io.dja.panama;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dja.panama.aggregator.ImmutablePocketRequest;
import io.dja.panama.aggregator.ImmutablePocketResponse;
import io.dja.panama.aggregator.PocketRequest;
import io.dja.panama.aggregator.PocketResponse;
import io.dja.panama.aggregator.PocketRetrievable;
import io.dja.panama.aggregator.PocketRetriever;
import io.dja.panama.aggregator.Retrievable;
import io.dja.panama.aggregator.Retriever;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static io.dja.panama.fixtures.TestFixtures.buildPocketRequest;
import static io.dja.panama.fixtures.TestFixtures.buildPocketResponse;
import static io.dja.panama.fixtures.TestFixtures.readFileToString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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