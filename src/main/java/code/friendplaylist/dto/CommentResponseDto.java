package code.friendplaylist.dto;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta contendo informações completas do comentário")
public class CommentResponseDto {

    @Schema(description = "ID único do comentário", example = "1")
    private Long id;

    @Schema(description = "Nome do autor do comentário", example = "João Silva")
    private String author;

    @Schema(description = "ID do autor no Spotify", example = "spotify_user_123")
    private String authorId;

    @Schema(description = "URL da imagem de perfil do autor", example = "https://i.scdn.co/image/ab67757000003b8255c25988a6ac314394d3fbf5")
    private String authorImageUrl;

    @Schema(description = "Texto do comentário", example = "Essa playlist é incrível! Adoro essas músicas.")
    private String text;

    @Schema(description = "Data e hora de criação do comentário", example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;

    public CommentResponseDto() {}

    public CommentResponseDto(Long id, String author, String authorId, String authorImageUrl, String text, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.authorId = authorId;
        this.authorImageUrl = authorImageUrl;
        this.text = text;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}