package com.example.demo3.Model;



import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Content.Content;

import java.util.ArrayList;

public class Channel {
    private ArrayList<Content> contents;
    private int Id;
    private String name;
    private String description;
    private String cover;
    private String ownerName;
    private ArrayList<Playlist> playlists;
    private ArrayList<User> subscribers;

    public Channel(String name, String description, String cover) {
        this.name = name;
        this.description = description;
        this.cover = cover;
        if (this.contents== null) {
            this.contents = new ArrayList<>();
        }

        if (this.subscribers == null) {
            this.subscribers = new ArrayList<>();
        }
        if (this.playlists == null) {
            this.playlists = new ArrayList<>();
        }
        playlists.add(new Playlist("All Contents"));
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return Id;
    }
    public String getDescription() {
        return description;
    }
    public String getCover() {
        return cover;
    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<User> getSubscribers() {
        return subscribers;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
    public void setSubscribers(ArrayList<User> subscribers) {
        this.subscribers = subscribers;
    }
}
