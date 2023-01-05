package vttp.paf.tvShowsDBExcercise.models;

import org.bson.Document;

public class Comment {
    
    private String commentText;
    private int rating;
    private String commentId;
    private String user;

    public String getCommentText() {        return commentText;    }
    public void setCommentText(String commentText) {        this.commentText = commentText;    }

    public int getRating() {        return rating;    }
    public void setRating(int rating) {        this.rating = rating;    }

    public String getCommentId() {        return commentId;    }
    public void setCommentId(String commentId) {        this.commentId = commentId;    }

    public String getUser() {        return user;    }
    public void setUser(String user) {        this.user = user;    }
    
    public static Comment createComment(Document doc){
        Comment comment = new Comment();
        comment.setCommentId(doc.getString("c_id"));
        comment.setCommentText(doc.getString("c_text"));
        comment.setRating(doc.getInteger("rating"));
        comment.setUser(doc.getString("user"));
        return comment;
    }
}
