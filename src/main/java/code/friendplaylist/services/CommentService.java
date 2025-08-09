package code.friendplaylist.services;

import code.friendplaylist.domain.Comment;
import code.friendplaylist.domain.User;
import code.friendplaylist.dto.CommentDto;
import code.friendplaylist.dto.CommentResponseDto;
import code.friendplaylist.repository.CommentRepository;
import code.friendplaylist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    
    private static final String ADMIN_USER_ID = "znoogh"; 

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommentResponseDto addComment(String playlistId, CommentDto commentDto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));

        Comment comment = new Comment(user, playlistId, commentDto.getText());
        Comment savedComment = commentRepository.save(comment);

        return convertToResponseDto(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPlaylistId(String playlistId) {
        List<Comment> comments = commentRepository.findByPlaylistIdOrderByCreatedAtDesc(playlistId);
        return comments.stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Transactional
    public CommentResponseDto editComment(Long commentId, CommentDto commentDto, String userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado com ID: " + commentId));

        // Verificar se o usuário é o dono do comentário
        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Você só pode editar seus próprios comentários");
        }

        comment.setText(commentDto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        
        Comment updatedComment = commentRepository.save(comment);
        return convertToResponseDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long commentId, String userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado com ID: " + commentId));

        // Verificar se o usuário é o dono do comentário OU se é o administrador
        boolean isOwner = comment.getUser().getId().equals(userId);
        boolean isAdmin = ADMIN_USER_ID.equals(userId);
        
        if (!isOwner && !isAdmin) {
            throw new RuntimeException("Você só pode deletar seus próprios comentários");
        }

        commentRepository.delete(comment);
    }

    private CommentResponseDto convertToResponseDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setAuthorId(comment.getUser().getId());
        dto.setAuthor(comment.getUser().getDisplayName());
        dto.setAuthorImageUrl(comment.getUser().getImageUrl());
        return dto;
    }

    public boolean isAdmin(String userId) {
        return ADMIN_USER_ID.equals(userId);
    }
}