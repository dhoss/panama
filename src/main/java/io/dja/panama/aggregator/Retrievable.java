package io.dja.panama.aggregator;

public interface Retrievable<T, U> {
    
    U get(T request);
    
}