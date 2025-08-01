package com.example.demo3.Model.Content;



public class NormalVideo extends Video {
    private VideoQuality quality;
    private VideoFormat format;

    public NormalVideo(String title, String description, String duration, String link, String cover, String subtitle){
        super(title, description, duration,link, cover,subtitle);
    }

    public VideoFormat getFormat() {
        return format;
    }
    public VideoQuality getQuality() {
        return quality;
    }
    public void setQuality(VideoQuality quality) {
        this.quality = quality;
    }
    public void setFormat(VideoFormat format) {
        this.format = format;
    }


}
