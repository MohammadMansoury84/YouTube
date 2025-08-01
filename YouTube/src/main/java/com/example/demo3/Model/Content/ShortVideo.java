package com.example.demo3.Model.Content;


public class ShortVideo extends Video {
    private String referenceMusicName;

    public ShortVideo(String title, String description, String duration, String link, String cover, String subtitle, String referenceMusicName){
        super( title, description, duration, link, cover,subtitle);
        this.referenceMusicName=referenceMusicName;
    }

    public String getReferenceMusicName() {
        return referenceMusicName;
    }
    public void setReferenceMusicName(String referenceMusicName) {
        this.referenceMusicName = referenceMusicName;
    }
}
