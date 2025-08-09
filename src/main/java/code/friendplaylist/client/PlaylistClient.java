package code.friendplaylist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import code.friendplaylist.client.playlist.PlaylistResponse;

@FeignClient(
    name = "spotify-client",
    url = "https://api.spotify.com/v1")
public interface PlaylistClient {

    @GetMapping("/me/playlists")
    PlaylistResponse getPlaylists(@RequestHeader("Authorization") String authorization);

    @GetMapping("/users/{user_id}/playlists")
    PlaylistResponse getUsersPlaylists(
        @RequestHeader("Authorization") String authorization,
        @PathVariable("user_id") String userId);
}