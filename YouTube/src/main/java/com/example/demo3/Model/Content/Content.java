package com.example.demo3.Model.Content;


import com.example.demo3.Model.Category;
import com.example.demo3.Model.Comment;

import java.util.ArrayList;
import java.util.Date;

public class Content {
    private int ID;
    private String title;
    private boolean isExclusive=false;
    private String description;
    private String duration;
    private int views;
    private int likes;
    private Date releaseDate;
    private Category category;
    private String link;
    private String cover;
    private ArrayList<Comment> comments;

    public Content(String title, String description, String duration, String link, String cover) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.category = category;
        this.link = link;
        this.cover = cover;
        if (this.comments==null){
        this.comments = new ArrayList<>();}
    }

    public boolean getIsExclusive(){
        return isExclusive;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public String getDuration() {
        return duration;
    }
    public Category getCategory() {
        return category;
    }
    public int getLikes() {
        return likes;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public int getViews() {
        return views;
    }
    public int getID() {
        return ID;
    }
    public String getCover() {
        return cover;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setViews(int views) {
        this.views = views;
    }

}


