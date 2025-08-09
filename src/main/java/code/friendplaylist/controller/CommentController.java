package code.friendplaylist.controller;

import code.friendplaylist.dto.CommentDto;
import code.friendplaylist.dto.CommentResponseDto;
import code.friendplaylist.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists/{playlistId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable String playlistId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal OAuth2User principal) {

        String userId = principal.getName();
        CommentResponseDto comment = commentService.addComment(playlistId, commentDto, userId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable String playlistId) {
        List<CommentResponseDto> comments = commentService.getCommentsByPlaylistId(playlistId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> editComment(
            @PathVariable String playlistId,
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal OAuth2User principal) {

        String userId = principal.getName();
        CommentResponseDto updatedComment = commentService.editComment(commentId, commentDto, userId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable String playlistId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal OAuth2User principal) {

        String userId = principal.getName();
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}