package code.friendplaylist.client.playlist;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaylistItem(    
    boolean collaborative,
    String description,
    @JsonProperty("external_urls") ExternalUrls externalUrls,
    String href,
    String id,
    List<Image> images,
    String name,
    Owner owner,
    @JsonProperty("public") boolean isPublic, // Mapeado de "public"
    @JsonProperty("snapshot_id") String snapshotId,
    TracksInfo tracks,
    String type,    String uri) {
    
}
