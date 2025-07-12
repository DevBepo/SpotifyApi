package code.friendplaylist.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.michaelthelin.spotify.model_objects.specification.Playlist;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {

    private List<Playlist> items;


     


}
