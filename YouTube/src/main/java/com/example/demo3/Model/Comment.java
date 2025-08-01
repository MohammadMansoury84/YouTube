package com.example.demo3.Model;



import com.example.demo3.Model.Account.User;

import java.time.LocalDateTime;

public class Comment {
    private User commenter;
    private String comment;
    private LocalDateTime date;
    private int commentId;

    public Comment(String comment, LocalDateTime date){
        this.comment=comment;
        this.commentId=commentId;
        this.date=date;
    }

    public String getComment() {
        return comment;
    }
    public int getCommentId() {
        return commentId;
    }
    public User getCommenter() {
        return commenter;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
