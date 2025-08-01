package com.example.demo3.Controller;


import com.example.demo3.Model.Account.NormalUser;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import com.example.demo3.Model.Playlist;

public class PlayListController {
    private static PlayListController playListController;
    private PlayListController(User user) {
    }
    public static PlayListController getPlayListController(User user) {
        if (playListController == null) {
            playListController = new PlayListController(user);
        }
        return playListController;
    }
    private User user;
    private Playlist playlist;
    public String AddContentToWatchLater(int contentId) {
        Content targetContent = null;
        Database db = Database.getDatabase();
        for (Content content : db.getDatabase().getContents()) {
            if (content.getID()==contentId) {
                targetContent = content;
                break;
            }
        }
        if (targetContent == null) {
            return "Content with ID " + contentId + " not found.";
        }


        if (user instanceof NormalUser) {
            NormalUser normalUser = (NormalUser) user;
            if (playlist.getWatchLater().size() <= normalUser.getMaxContentsPerPl()) {
                playlist.getWatchLater().add(targetContent);
            }
            else   return "Normal user cannot add more than " + normalUser.getMaxContentsPerPl() + " contents to a playlist.";
        }
        else {
            playlist.getWatchLater().add(targetContent);
        }

        return "Content '" + targetContent.getTitle() + "' was added successfully to playlist '" ;
    }
    public String AddContentToLiked(  int contentId) {
        Content targetContent = null;
        Database db = Database.getDatabase();
        for (Content content : db.getDatabase().getContents()) {
            if (content.getID()==contentId) {
                targetContent = content;
                break;
            }
        }
        if (targetContent == null) {
            return "Content with ID " + contentId + " not found.";
        }


        if (user instanceof NormalUser) {
            NormalUser normalUser = (NormalUser) user;
            if (playlist.getWatchLater().size() <= normalUser.getMaxContentsPerPl()) {
                playlist.getLiked().add(targetContent);
            }
            else   return "Normal user cannot add more than " + normalUser.getMaxContentsPerPl() + " contents to a playlist.";
        }
        else {
            playlist.getLiked().add(targetContent);
        }

        return "Content '" + targetContent.getTitle() + "' was added successfully to playlist '" ;
    }
}
