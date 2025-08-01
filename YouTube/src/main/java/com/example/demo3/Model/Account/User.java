package com.example.demo3.Model.Account;


import com.example.demo3.Model.Category;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Playlist;

import java.util.ArrayList;

public abstract class User extends Account {
    private int credit;
    private ArrayList<Playlist> playlists;
    private Channel channel;
    private ArrayList<Channel> subscriptions;
    private ArrayList<Category> favoriteCategories;
    private boolean isBanned=false;
    private boolean isPremium=false;

    public User(String userName, String password, String name, String lastname, String email, String phoneNumber, String profileCover){
        super(userName, password,name,lastname, email, phoneNumber, profileCover);
        if (this.subscriptions==null){
        this.subscriptions=new ArrayList<>();}
        if (this.playlists==null){
        this.playlists=new ArrayList<>();}
        if (this.favoriteCategories==null){
        this.favoriteCategories=new ArrayList<>();}
    }

    public boolean isBanned(){
        return isBanned;
    }
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public Channel getChannel() {
        return channel;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<Category> getFavoriteCategories() {
        return favoriteCategories;
    }
    public ArrayList<Channel> getSubscriptions() {
        return subscriptions;
    }
    public int getCredit() {
        return credit;
    }
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public void setFavoriteCategories(ArrayList<Category> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }
    public void setSubscriptions(ArrayList<Channel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
