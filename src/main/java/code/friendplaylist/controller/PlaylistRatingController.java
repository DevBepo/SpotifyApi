package code.friendplaylist.controller;

import code.friendplaylist.dto.AverageRatingDto;
import code.friendplaylist.dto.RatingDto;
import code.friendplaylist.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRatingController {

    private final RatingService ratingService;

    @Autowired
    public PlaylistRatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{playlistId}/rate")
    public ResponseEntity<Void> ratePlaylist(@PathVariable String playlistId, @RequestBody RatingDto ratingDto, @AuthenticationPrincipal OAuth2User principal) {
        String userId = principal.getName();
        ratingService.saveRating(playlistId, ratingDto, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{playlistId}/average")
    public ResponseEntity<AverageRatingDto> getAverageRating(@PathVariable String playlistId) {
        AverageRatingDto averageRating = ratingService.getAverageRating(playlistId);
        return ResponseEntity.ok(averageRating);
    }
}