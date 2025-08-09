package code.friendplaylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import code.friendplaylist.client.TracksClient;
import code.friendplaylist.client.tracks.tracksDTO.SpotifyTrackResponse;
import code.friendplaylist.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Tracks", description = "Operações relacionadas às faixas das playlists")
@SecurityRequirement(name = "oauth2")
public class TracksController {
    private final TracksClient tracksClient;
    private final UserService userService;

     public TracksController(TracksClient tracksClient, UserService userService) {
         this.tracksClient = tracksClient;
         this.userService = userService;
     }

     @GetMapping("/api/playlists/{playlist_id}/tracks")
     @Operation(
         summary = "Obter faixas de uma playlist",
         description = "Retorna todas as faixas de uma playlist específica do Spotify"
     )
     @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Faixas da playlist retornadas com sucesso",
                 content = @Content(schema = @Schema(implementation = SpotifyTrackResponse.class))),
         @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
         @ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
         @ApiResponse(responseCode = "403", description = "Token de acesso inválido ou sem permissão para acessar a playlist")
     })
     public ResponseEntity<SpotifyTrackResponse> getPlaylistTracks(
            @Parameter(description = "ID da playlist no Spotify", required = true)
            @PathVariable("playlist_id") String playlistId,
            @Parameter(hidden = true)
            OAuth2AuthenticationToken authentication) {
        String accessToken = userService.getAccessToken(authentication);
        SpotifyTrackResponse response = tracksClient.getTracks("Bearer " + accessToken, playlistId);
        return ResponseEntity.ok(response);
     }
}
 