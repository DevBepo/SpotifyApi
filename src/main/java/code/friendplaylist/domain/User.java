package code.friendplaylist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    private String id;
    private String displayName;
    private String email;
    private String country;
    private Integer followers;
    private String imageUrl;
    private String spotifyUri;
    private String nickname;
}
