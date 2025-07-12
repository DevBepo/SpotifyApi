package code.friendplaylist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "PlaylistSpotifyClient",
    url = "https://api.spotify.com"
)
public interface PlaylistSpotifyClient {

    @GetMapping(value = "v1/me/playlists")
    PlaylistResponse getReleases(@RequestHeader("Authorization") String authorization);
}
