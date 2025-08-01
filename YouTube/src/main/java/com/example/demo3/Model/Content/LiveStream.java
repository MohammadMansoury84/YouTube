package com.example.demo3.Model.Content;


import java.util.Date;

public class LiveStream extends Video {
    private int onlineViewrs;
    private Date scheduledTime;

    public LiveStream(String title, String description, String duration, String link, String cover, String subtitle){
        super( title, description, duration, link, cover,subtitle);
    }
    public Date getScheduledTime() {
        return scheduledTime;
    }
    public int getOnlineViewrs() {
        return onlineViewrs;
    }
    public void setOnlineViewrs(int onlineViewrs) {
        this.onlineViewrs = onlineViewrs;
    }
    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
