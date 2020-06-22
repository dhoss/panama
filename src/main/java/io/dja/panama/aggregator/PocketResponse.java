package io.dja.panama.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutablePocketResponse.class)
@JsonDeserialize(as = ImmutablePocketResponse.class)
public abstract class PocketResponse extends Request {
    
    @JsonProperty
    public abstract int status();
    @JsonProperty
    public abstract Map<String, PocketItem> list();
    @JsonProperty
    public abstract int complete();
}
