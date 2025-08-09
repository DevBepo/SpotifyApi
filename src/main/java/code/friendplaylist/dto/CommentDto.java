package code.friendplaylist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para criação e edição de comentários")
public class CommentDto {

    @Schema(description = "Texto do comentário", example = "Essa playlist é incrível! Adoro essas músicas.")
    @NotBlank(message = "O texto do comentário não pode estar vazio")
    @Size(min = 1, max = 1000, message = "O comentário deve ter entre 1 e 1000 caracteres")
    private String text;

    public CommentDto() {}

    public CommentDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}