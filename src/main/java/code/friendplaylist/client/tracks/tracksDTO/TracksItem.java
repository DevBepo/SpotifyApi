package code.friendplaylist.client.tracks.tracksDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import code.friendplaylist.client.playlistDTO.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class TracksItem { 

    @JsonProperty("added_at")
    private String addedAt;

    @JsonProperty("added_by")
    private Owner addedBy;

    @JsonProperty("is_local")
    private boolean isLocal;

    private Tracks track;
}