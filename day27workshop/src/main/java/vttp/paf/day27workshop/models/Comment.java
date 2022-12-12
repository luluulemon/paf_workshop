package vttp.paf.day27workshop.models;


public class Comment {
    
    private String comment;
    private Double rating;
    private String posted;


    public String getPosted() {
        return posted;
    }
    public void setPosted(String posted) {
        this.posted = posted;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }


}
