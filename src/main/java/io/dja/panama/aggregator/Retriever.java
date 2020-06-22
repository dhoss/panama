package io.dja.panama.aggregator;

public abstract class Retriever<T, U> {
    
    public Retrievable<T, U> retriever;
    
    public Retriever(Retrievable<T,U> retriever) {
        this.retriever = retriever;
    }
    
    public U retrieve(T request) {
        return retriever.get(request);
    }
}