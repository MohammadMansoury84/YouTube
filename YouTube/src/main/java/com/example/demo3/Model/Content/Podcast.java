package com.example.demo3.Model.Content;



public class Podcast extends Content {
    private String owner;

    public Podcast(String title, String description, String duration, String link, String cover, String owner){
        super(title,description,duration,link,cover);
        this.owner=owner;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
