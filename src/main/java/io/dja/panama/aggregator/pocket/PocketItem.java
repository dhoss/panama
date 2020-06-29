package io.dja.panama.aggregator.pocket;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePocketItem.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ImmutablePocketItem.class)
public abstract class PocketItem {
    @JsonProperty("item_id")
    public abstract String itemId();
    @JsonProperty("resolved_id")
    public abstract String resolvedId();
    @JsonProperty("given_url")
    public abstract String givenUrl();
    @JsonProperty("given_title")
    public abstract String givenTitle();
    @JsonProperty
    public abstract String favorite();
    @JsonProperty
    public abstract String status();
    @JsonProperty("resolved_title")
    public abstract String resolvedTitle();
    @JsonProperty("resolved_url")
    public abstract String resolvedUrl();
    @JsonProperty
    public abstract String excerpt();
    @JsonProperty("is_article")
    public abstract String isArticle();
    @JsonProperty("has_video")
    public abstract String hasVideo();
    @JsonProperty("has_image")
    public abstract String hasImage();
    @JsonProperty("word_count")
    public abstract String wordCount();
    /**
     *
     * "item_id": "229279689",
     * 			"resolved_id": "229279689",
     * 			"given_url": "http:\/\/www.grantland.com\/blog\/the-triangle\/post\/_\/id\/38347\/ryder-cup-preview",
     * 			"given_title": "The Massive Ryder Cup Preview - The Triangle Blog - Grantland",
     * 			"favorite": "0",
     * 			"status": "0",
     * 			"resolved_title": "The Massive Ryder Cup Preview",
     * 			"resolved_url": "http:\/\/www.grantland.com\/blog\/the-triangle\/post\/_\/id\/38347\/ryder-cup-preview",
     * 			"excerpt": "The list of things I love about the Ryder Cup is so long that it could fill a (tedious) novel, and golf fans can probably guess most of them.",
     * 			"is_article": "1",
     * 			"has_video": "1",
     * 			"has_image": "1",
     * 			"word_count": "3197"
     */
}
