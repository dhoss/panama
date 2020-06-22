package io.dja.panama.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketRetriever extends Retriever<PocketRequest, PocketResponse> {
    
    @Autowired
    private Retrievable<PocketRequest, PocketResponse> pocketRetrievable;
    
    @Autowired
    public PocketRetriever(Retrievable<PocketRequest, PocketResponse> retrievable) {
        super(retrievable);
    }
    
    public PocketResponse retrieve(PocketRequest request) {
        return this.pocketRetrievable.get(request);
    }
}