package io.dja.panama.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePocketRequest.class)
@JsonDeserialize(as = ImmutablePocketRequest.class)
public abstract class PocketRequest extends Request {
    @JsonProperty("consumer_key")
    public abstract String consumerKey();
    @JsonProperty("access_token")
    public abstract String accessToken();
    @JsonProperty
    public abstract int count();
    @JsonProperty
    public abstract String detailType();
}
