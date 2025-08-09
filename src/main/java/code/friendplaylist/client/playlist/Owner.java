package code.friendplaylist.client.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Owner(
    @JsonProperty("display_name") String displayName,
    @JsonProperty("external_urls") ExternalUrls externalUrls,
    String href,
    String id,
    String type,
    String uri
) {
    
}