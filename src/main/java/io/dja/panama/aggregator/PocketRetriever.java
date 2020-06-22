package io.dja.panama.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketRetriever extends Retriever<ImmutablePocketRequest, ImmutablePocketResponse> {
   
    @Autowired
    private Retrievable<ImmutablePocketRequest, ImmutablePocketResponse> pocketRetrievable;
    
    public PocketRetriever(Retrievable<ImmutablePocketRequest, ImmutablePocketResponse> retrievable) {
        this.pocketRetrievable = retrievable;
    }
    
    public ImmutablePocketResponse retrieve(ImmutablePocketRequest request) {
        return pocketRetrievable.get(request);
    }
}