package code.friendplaylist.dto;

public class CommentDto {
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