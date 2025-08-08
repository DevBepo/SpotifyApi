package code.friendplaylist.dto;

public class AverageRatingDto {

    private double averageRating;
    private long ratingCount;

    public AverageRatingDto(double averageRating, long ratingCount) {
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
    }

    // Getters and Setters
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(long ratingCount) {
        this.ratingCount = ratingCount;
    }
}
