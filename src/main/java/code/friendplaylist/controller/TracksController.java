package code.friendplaylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import code.friendplaylist.client.TracksClient;
import code.friendplaylist.client.tracks.tracksDTO.SpotifyTrackResponse;
import code.friendplaylist.services.UserService;

@RestController
public class TracksController {
    private final TracksClient tracksClient;
    private final UserService userService;

     public TracksController(TracksClient tracksClient, UserService userService) {
         this.tracksClient = tracksClient;
         this.userService = userService;
     }

     @GetMapping("/api/playlists/{playlist_id}/tracks")
     public ResponseEntity<SpotifyTrackResponse> getPlaylistTracks(
            @PathVariable("playlist_id") String playlistId,
            OAuth2AuthenticationToken authentication) {
        String accessToken = userService.getAccessToken(authentication);
        SpotifyTrackResponse response = tracksClient.getTracks("Bearer " + accessToken, playlistId);
        return ResponseEntity.ok(response);
     }
}
 