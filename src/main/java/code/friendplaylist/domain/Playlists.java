package code.friendplaylist.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Playlists {

    @JsonProperty("collaborative")
    private Boolean isCollaborative; 
    private String description;
    private String id;
    private String name;
    private List<Image> images;
    private Owner owner;
    private Tracks tracks;
    private String uri;

    public static class Image {
        private String url;
    }

    public static class Owner {
        @JsonProperty("display_name")   
        private String displayName;
    }

    public static class Tracks {
        private int total;
    }
}
