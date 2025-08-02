package code.friendplaylist.client.tracks.tracksDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import code.friendplaylist.client.playlistDTO.ExternalUrls;
import code.friendplaylist.client.playlistDTO.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class Album {
 @JsonProperty("album_type")
 private String albumType;

 @JsonProperty("total_tracks")
 private int totalTracks;

 @JsonProperty("available_markets")
 private List<String> availableMarkets;

 @JsonProperty("external_urls")
 private ExternalUrls externalUrls;

 private String href;
 private String id;
 private List<Image> images;
 private String name;

 @JsonProperty("release_date")
 private String releaseDate;

 @JsonProperty("release_date_precision")
 private String releaseDatePrecision;

 private Restrictions restrictions;
 private String type;
 private String uri;
 private List<Artist> artists;
}
