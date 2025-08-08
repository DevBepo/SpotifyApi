package code.friendplaylist.dto;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private String author;
    private String authorImageUrl;
    private String text;
    private LocalDateTime createdAt;

    public CommentResponseDto() {}

    public CommentResponseDto(Long id, String author, String authorImageUrl, String text, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
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