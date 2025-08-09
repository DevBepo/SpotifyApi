package code.friendplaylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import code.friendplaylist.client.PlaylistClient;
import code.friendplaylist.client.playlist.PlaylistResponse;
import code.friendplaylist.services.UserService;

@RestController
public class PlaylistsController {
     private final UserService userService;
     private final PlaylistClient playlistClient;
     public PlaylistsController(UserService userService, PlaylistClient playlistClient) {
         this.userService = userService;
         this.playlistClient = playlistClient;
     }

     @GetMapping("/playlists")
     public ResponseEntity<PlaylistResponse> getPlaylists(OAuth2AuthenticationToken authentication) {
        String accessToken = userService.getAccessToken(authentication);
        PlaylistResponse response = playlistClient.getPlaylists("Bearer " + accessToken);
        return ResponseEntity.ok(response);
     }

     @GetMapping("/users/{user_id}/playlists")
     public ResponseEntity<PlaylistResponse> getUsersPlaylists(
            @PathVariable("user_id") String userId,
            OAuth2AuthenticationToken authentication) {
                String accessToken = userService.getAccessToken(authentication);
                System.out.println("Buscando playlists para o usuário: " + userId);
                PlaylistResponse response = playlistClient.getUsersPlaylists("Bearer " + accessToken, userId) ;
                return ResponseEntity.ok(response);
            }
}