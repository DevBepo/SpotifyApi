package code.friendplaylist.client.tracks.tracksDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import code.friendplaylist.client.playlistDTO.ExternalUrls;


@JsonIgnoreProperties(ignoreUnknown = true)
public record Tracks(
    Album album,
    List<Artist> artists,
    
    @JsonProperty("available_markets") 
    List<String> availableMarkets,
    
    @JsonProperty("disc_number") 
    int discNumber,
    
    @JsonProperty("duration_ms") 
    int durationMs,
    
    boolean explicit,
    
    @JsonProperty("external_ids") 
    ExternalIds externalIds,
    
    @JsonProperty("external_urls") 
    ExternalUrls externalUrls,
    
    String href,
    String id,
    
    @JsonProperty("is_playable") 
    boolean isPlayable,
    
    Restrictions restrictions,
    String name,
    int popularity,
    
    @JsonProperty("preview_url") 
    String previewUrl,
    
    @JsonProperty("track_number") 
    int trackNumber,
    
    String type,
    String uri,
    
    @JsonProperty("is_local") 
    boolean isLocal
) {
    
}