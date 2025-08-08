package code.friendplaylist.controller;

import code.friendplaylist.dto.RatingDto;
import code.friendplaylist.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/playlists/{playlistId}/rate")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Void> ratePlaylist(@PathVariable String playlistId, @RequestBody RatingDto ratingDto, @AuthenticationPrincipal OAuth2User principal) {
        String nickname = principal.getAttribute("login");
        ratingService.saveRating(playlistId, ratingDto, nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getRating(@PathVariable String playlistId, @AuthenticationPrincipal OAuth2User principal) {
        String nickname = principal.getAttribute("login");
        return ratingService.getRating(playlistId, nickname)
                .map(score -> ResponseEntity.ok().body(Map.of("rating", score)))
                .orElse(ResponseEntity.notFound().build());
    }
}
