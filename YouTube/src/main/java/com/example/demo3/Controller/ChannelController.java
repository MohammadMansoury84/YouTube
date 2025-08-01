package com.example.demo3.Controller;


import com.example.demo3.Model.Account.NormalUser;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Category;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Content.*;
import com.example.demo3.Model.Database;
import com.example.demo3.Model.Playlist;

import java.util.ArrayList;

public class ChannelController {

    public ArrayList<Content> allContents;
    public Channel channel;
    public User user;
    public NormalUser normalUser;
    private static ChannelController channelController;
    public Content content;
    public int numOfChannel = 0;
    public int numOfPlaylist = 0;
    public int unmOfContent = 0;
    public Database database;

    private ChannelController(User user) {
this.user=user;
    }

    public static ChannelController getChannelController(User user) {
        if (channelController == null) {
            channelController = new ChannelController(user);
        }
        return channelController;
    }

    public String createChannel(String name, String description, String cover) {
        Channel channel = new Channel(name, description, cover);
        user.setChannel(channel);
        channel.setOwnerName(user.getUserName());
        channel.setId(generateUniqueId());
        channel.setOwnerName(user.getUserName());
        database.getDatabase().getChannels().add(channel);
        return "Channel was created successfully.";
    }

    public int generateUniqueId() {
        numOfChannel++;
        return numOfChannel;
    }

    public int generateUniquePlayListId() {
        numOfPlaylist++;
        return numOfPlaylist;
    }
    public int generateUniqueContentId() {

        unmOfContent++;
        return unmOfContent;
    }

    public String ViewChannel() {
        StringBuilder result = new StringBuilder();
        result.append("Channel ID: ").append(channel.getId()).append("\n");
        result.append("Channel Name: ").append(channel.getName()).append("\n");
        result.append("Description: ").append(channel.getDescription()).append("\n");
        result.append("Owner Name: ").append(channel.getOwnerName()).append("\n");
        result.append("Cover: ").append(channel.getCover()).append("\n");
        result.append("Subscribers Count: ").append(channel.getSubscribers().size());
        result.append("\nPlaylists:\n");
        if (channel.getPlaylists() != null && !channel.getPlaylists().isEmpty()) {
            for (Playlist playlist : channel.getPlaylists()) {
                result.append(" - ").append(playlist.getName()).append(" (")
                        .append(playlist.getContents().size()).append(" contents)\n");
            }
        } else {
            result.append("No playlists available.\n");
        }
        result.append("\nContents:\n");
        ArrayList<Content> contents = getChannelContents(channel);
        if (contents != null && !contents.isEmpty()) {
            for (Content content : contents) {
                result.append(" - Title: ").append(content.getTitle())
                        .append(", Category: ").append(content.getCategory())
                        .append(", Views: ").append(content.getViews())
                        .append(", Likes: ").append(content.getLikes())
                        .append("\n");
            }
        } else {
            result.append("No contents available.\n");
        }
        return result.toString();
    }

    public String updateChannel(String editType, String descriptionOrName) {
        if (editType.equals("N")) {
            channel.setName(descriptionOrName);
            return "Channel was edited successfully.";
        } else {
            channel.setDescription(descriptionOrName);
            return "Channel was created successfully.";
        }
    }


    public String subChannel(int channelId) {
        Channel channel = getChannelById(channelId);
        if (channel != null) {
            user.getSubscriptions().add(channel);
            channel.getSubscribers().add(user);
        }
        return "Subscribed";
    }

    public String unsubChannel(int channelId) {
        Channel channel = getChannelById(channelId);
        if (channel != null) {
            user.getSubscriptions().remove(channel);
            channel.getSubscribers().remove(user);
        }
        return "UnSubscribed";
    }

    public ArrayList<Content> getChannelContents(Channel channel) {
        ArrayList<Content> contents = new ArrayList<>();
        if (this.channel.getPlaylists() != null) {
            for (Playlist playlist : channel.getPlaylists()) {
                if (playlist.getContents() != null) {
                    contents.addAll(playlist.getContents());
                }
            }
        }
        return contents;
    }

    public Channel getChannelById(int id) {
        Database db = Database.getDatabase().getDatabase();
        for (User user : db.getDatabase().getUsers()) {
            if (user.getChannel() != null && user.getChannel().getId() == id) {
                return user.getChannel();
            }
        }
        return null;
    }

    public String ShowChannelContent() {
        StringBuilder result = new StringBuilder();
        ArrayList<Content> contents = new ArrayList<>();
        if (channel.getPlaylists() != null) {
            for (Playlist playlist : channel.getPlaylists()) {
                if (playlist.getContents() != null) {
                    contents.addAll(playlist.getContents());
                }
            }
        }
        if (contents != null && !contents.isEmpty()) {
            for (Content content : contents) {
                result.append(" - Title: ").append(content.getTitle())
                        .append(", Category: ").append(content.getCategory())
                        .append(", Views: ").append(content.getViews())
                        .append(", Likes: ").append(content.getLikes())
                        .append("\n");
            }
        } else {
            result.append("No contents available.\n");
        }

        return result.toString();
    }

    public String ShowChannelSubscribers() {
        StringBuilder result = new StringBuilder();
        if (channel.getSubscribers() != null && !channel.getSubscribers().isEmpty()) {
            result.append("Subscribers of Channel '").append(channel.getName()).append("':\n");
            for (User subscriber : channel.getSubscribers()) {
                result.append(" - Name: ").append(subscriber.getUserName())
                        .append(", Email: ").append(subscriber.getEmail())
                        .append("\n");
            }
        } else {
            result.append("No subscribers available for Channel '").append(channel.getName()).append("'.\n");
        }
        return result.toString();
    }

    public String CreatePlaylist(String channelOrUser, String name) {
        normalUser= (NormalUser) user;
        if (channelOrUser.equals("U")) {
            Playlist playlist = new Playlist(name);
            playlist.setId(generateUniquePlayListId());
            if (channel.getPlaylists() == null) {
                channel.setPlaylists(new ArrayList<>());
            }
            user.getPlaylists().add(playlist);
            return "Playlist was created successfully.";
        } else {
            if (user instanceof NormalUser) {
                if (normalUser.getPlaylists().size() <= normalUser.getMaxPlayList()) {
                    Playlist playlist = new Playlist(name);
                    playlist.setId(generateUniquePlayListId());
                    user.getPlaylists().add(playlist);
                    return "Playlist was created successfully.";
                } else return "Normal user cannot create more than" + normalUser.getMaxPlayList() + "playlists.";
            } else {
                Playlist playlist = new Playlist(name);
                playlist.setId(generateUniquePlayListId());
                channel.getPlaylists().add(playlist);
                return "Playlist was created successfully.";
            }

        }
    }

    public String AddContentToPlaylist(int playlistId, int contentId) {
        Content targetContent = null;
        Database db = Database.getDatabase().getDatabase();
        for (Content content : db.getDatabase().getContents()) {
            if (content.getID() == contentId) {
                targetContent = content;
                break;
            }
        }
        if (targetContent == null) {
            return "Content with ID " + contentId + " not found.";
        }
        Playlist targetPlaylist = null;
        for (Playlist playlist : user.getPlaylists()) {
            if (playlist.getId() == playlistId) {
                targetPlaylist = playlist;
                break;
            }
        }
        if (targetPlaylist == null) {
            return "Playlist with ID " + playlistId + " not found for the user.";
        }
        if (user instanceof NormalUser) {
            NormalUser normalUser = (NormalUser) user;
            if (targetPlaylist.getContents().size() <= normalUser.getMaxContentsPerPl()) {
                targetPlaylist.getContents().add(targetContent);
            } else
                return "Normal user cannot add more than " + normalUser.getMaxContentsPerPl() + " contents to a playlist.";
        } else {
            targetPlaylist.getContents().add(targetContent);
        }

        return "Content '" + targetContent.getTitle() + "' was added successfully to playlist '" + targetPlaylist.getName() + "'.";
    }


    public String PublishPodcast( String specialOrRegular, String title, String description, String duration, String category, String contentLink, String cover, String podcaster){
        Podcast podcast = new Podcast(title, description, duration, contentLink, cover, podcaster);
        user.getChannel().getPlaylists().get(0).addContent(podcast);
        database.getDatabase().getContents().add(podcast);
        if(specialOrRegular.equals("Y")){
        podcast.setExclusive(true);}
        else podcast.setExclusive(false);
        podcast.setID(generateUniqueContentId());
        podcast.setCategory(Category.valueOf(category));
        return "Podcast published successfully: " + title;
    }
    public String PublishNormalVideo( String specialOrRegular, String title, String description, String duration, String category, String contentLink, String cover, String subtitle,String quality,String format){
        NormalVideo normalVideo=new NormalVideo(title, description, duration, contentLink, cover,subtitle);
        user.getChannel().getPlaylists().get(0).addContent(normalVideo);
        database.getDatabase().getContents().add(normalVideo);
        if(specialOrRegular.equals("Y")){
            normalVideo.setExclusive(true);}
        else normalVideo.setExclusive(false);
        normalVideo.setID(generateUniqueContentId());
        normalVideo.setCategory(Category.valueOf(category));
        normalVideo.setFormat(VideoFormat.valueOf(format));
        normalVideo.setQuality(VideoQuality.valueOf("Q"+quality));
        return "NormalVideo published successfully: " + title;
    }
    public String PublishShortVideo( String specialOrRegular, String title, String description, String duration, String category, String contentLink, String cover, String subtitle,String audioTitle) {
        ShortVideo shortVideo=new ShortVideo(title, description, duration, contentLink, cover,subtitle,audioTitle);
        user.getChannel().getPlaylists().get(0).addContent(shortVideo);
        database.getDatabase().getContents().add(shortVideo);
        if(specialOrRegular.equals("Y")){
            shortVideo.setExclusive(true);}
        else shortVideo.setExclusive(false);
        shortVideo.setID(generateUniqueContentId());
        shortVideo.setCategory(Category.valueOf(category));
        return "ShortVideo published successfully: " + title;
    }
    public String PublishLiveStream( String specialOrRegular, String title, String description, String duration,String category, String contentLink, String cover, String subtitle,String scheduledDate) {
    LiveStream liveStream=new LiveStream(title, description, duration, contentLink, cover,subtitle);
        user.getChannel().getPlaylists().get(0).addContent(liveStream);
        database.getDatabase().getContents().add(liveStream);
        if(specialOrRegular.equals("Y")){
            liveStream.setExclusive(true);}
        else liveStream.setExclusive(false);
        liveStream.setID(generateUniqueContentId());
        liveStream.setCategory(Category.valueOf(category));
        return  "LiveStream published successfully: " + title;
    }



}














