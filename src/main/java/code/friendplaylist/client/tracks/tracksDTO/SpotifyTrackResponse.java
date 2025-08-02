package code.friendplaylist.client.tracks.tracksDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyTrackResponse{

    private String href;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;
    private List<TracksItem> items;

}