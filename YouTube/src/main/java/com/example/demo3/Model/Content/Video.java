package com.example.demo3.Model.Content;



public abstract class Video extends Content {
    private String subtitle;
    public Video( String title, String description, String duration, String link, String cover, String subtitle) {
        super( title, description, duration, link, cover);
        this.subtitle = subtitle;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
