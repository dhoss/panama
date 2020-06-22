package io.dja.panama;

import io.dja.panama.aggregator.PocketRequest;
import io.dja.panama.aggregator.PocketResponse;
import io.dja.panama.aggregator.Retrievable;
import io.dja.panama.aggregator.Retriever;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.dja.panama.fixtures.TestFixtures.POCKET_REQUEST;
import static io.dja.panama.fixtures.TestFixtures.POCKET_RESPONSE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PocketRetrieverTest {
    
    @Mock
    private Retrievable<PocketRequest, PocketResponse> retrievable;
    
    @InjectMocks
    private Retriever<PocketRequest, PocketResponse> retriever;
    
    @Before
    public void setup() {
        when(retrievable.get(POCKET_REQUEST)).thenReturn(POCKET_RESPONSE);
    }
    
    @Test
    public void testRetrieve() {
        assertEquals(POCKET_RESPONSE, retriever.retrieve(POCKET_REQUEST));
        verify(retrievable, times(1)).get(POCKET_REQUEST);
    }
}