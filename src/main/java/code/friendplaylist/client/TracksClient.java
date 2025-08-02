package code.friendplaylist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import code.friendplaylist.client.tracks.tracksDTO.SpotifyTrackResponse;

@FeignClient(
    name = "track-client",
    url = "https://api.spotify.com/v1"
)
public interface TracksClient {

    @GetMapping("/playlists/{playlist_id}/tracks")
    SpotifyTrackResponse getTracks(
        @RequestHeader("Authorization") String authorization,
        @PathVariable("playlist_id") String playlistId
    );


}
