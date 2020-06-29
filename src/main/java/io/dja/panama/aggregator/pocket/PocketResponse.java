package io.dja.panama.aggregator.pocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.dja.panama.aggregator.Request;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(as = ImmutablePocketResponse.class)
@JsonDeserialize(as = ImmutablePocketResponse.class)
public abstract class PocketResponse extends Request {
    @JsonProperty
    public abstract int status();
    @JsonProperty
    public abstract Map<String, ImmutablePocketItem> list();
    @JsonProperty
    public abstract int complete();
}
