package code.friendplaylist.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String playlistId;

    @Column(nullable = false)
    private int score; // 1 a 5

    public Rating() {}

    public Rating(User user, String playlistId, int score) {
        this.user = user;
        this.playlistId = playlistId;
        this.score = score;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
