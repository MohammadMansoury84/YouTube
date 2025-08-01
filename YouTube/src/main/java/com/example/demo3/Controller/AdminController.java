package com.example.demo3.Controller;


import com.example.demo3.Model.Account.Admin;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import com.example.demo3.Model.Playlist;
import com.example.demo3.Model.Report;

import java.util.ArrayList;
import java.util.Comparator;

public class AdminController {
    private static AdminController adminController;
    private AdminController() {

    }
    public static AdminController getAdminController() {
        if (adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    public User user;
    public Database database;
    public Admin admin;
    public Report report;

    public String banUser(int userId) {
        for (User user : database.getDatabase().getUsers()) {
            if (user.getId() == userId) {
                user.setBanned(true);
                return "User banned successfully";
            }
        }
        return "can not find user";
    }
    public String unbanUser(int userId) {
        for (User user : database.getDatabase().getUsers()) {
            if (user.getId() == userId) {
                if (!user.isBanned()) {
                    return "User is not banned";
                }
                user.setBanned(false);
                return "User unbanned successfully";
            }
        }
        return "can not find user";
    }
    public ArrayList<Content>   getPopularContents() {
        ArrayList<Content> contents = database.getDatabase().getContents();
        if (contents == null || contents.isEmpty()) {
        }
        contents.sort(Comparator.comparingInt(Content::getLikes).reversed());
        return contents;
    }
    public  ArrayList<Channel> getPopularChannels() {
        ArrayList<Channel> channels = new ArrayList<>();
        for (User user : database.getDatabase().getUsers()) {
            if (user.getChannel() != null) {
                channels.add(user.getChannel());
            }
        }
        channels.sort((c1, c2) -> Integer.compare( c2.getSubscribers().size(),  c1.getSubscribers().size() ));
        if (channels.isEmpty()) {
        }

        return channels;
    }
    public String showAllUsers() {
        ArrayList<User> users = database.getDatabase().getUsers();
        if (users == null || users.isEmpty()) {
            return "No users found in the database.";
        }
        StringBuilder result = new StringBuilder("List of Users:\n");
        for (User user : users) {
            result.append("- Username: ").append(user.getUserName())
                    .append("\n  Full Name: ").append(user.getName()).append(" ").append(user.getName())
                    .append("\n  Email: ").append(user.getEmail())
                    .append("\n  Phone: ").append(user.getPhoneNumber())
                    .append("\n  Channel Name: ").append(user.getChannel() != null ? user.getChannel().getName() : "No Channel")
                    .append("\n\n");
        }

        return result.toString();
    }
    public String showAllReports() {
        ArrayList<Report> reports = database.getDatabase().getReports();
        if (reports == null || reports.isEmpty()) {
            return "No reports found in the database.";
        }
        StringBuilder result = new StringBuilder("List of Reports:\n");
        for (Report report : reports) {
            result.append("- Report ID: ").append(report.getId())
                    .append("\n  Explanation: ").append(report.getReportComment())
                    .append("\n   Content ID: ").append(report.getContentId())
                    .append("\n\n");
        }

        return result.toString();
    }
    public String showAllContents() {

        ArrayList<Content> contents = database.getDatabase().getContents();
        if (contents == null || contents.isEmpty()) {
            return "No contents found in the database.";
        }
        StringBuilder result = new StringBuilder("List of Contents:\n");
        for (Content content : contents) {
            result.append("- Title: ").append(content.getTitle())
                    .append("\n  Likes: ").append(content.getLikes())
                    .append("\n  Views: ").append(content.getViews())
                    .append("\n  Category: ").append(content.getCategory())
                    .append("\n\n");
        }

        return result.toString();
    }
    public String handleReport(String rejectOrAccept, int reportID) {
        Report report = null;
        for (Report r : database.getDatabase().getReports()) {
            if (r.getId() == reportID) {
                report = r;
                break;
            }
        }
        if (report == null) {
            return "Report with ID not found.";
        }
        if (rejectOrAccept.equals("A")) {
            int contentID = report.getContentId();

            ArrayList<Content> contents = database.getDatabase().getContents();
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).getID() == contentID) {
                    contents.remove(i);
                    break;
                }
            }
            for (User user : database.getDatabase().getUsers()) {
                Channel channel = user.getChannel();
                if (channel != null) {
                    for (Playlist playlist : channel.getPlaylists()) {
                        for (int i = 0; i < playlist.getContents().size(); i++) {
                            if (playlist.getContents().get(i).getID() == contentID) {
                                playlist.getContents().remove(i);
                                break;
                            }
                        }
                    }
                }
            }
            return "The report was approved and the content with ID was removed from the database and channels.";
        }

        return "Report with ID " + reportID + " was rejected.";
    }
    public String viewAdminAccount() {
        if (admin == null) {
            return "No admin account found in the system.";
        }

        StringBuilder result = new StringBuilder("Admin Account Information:\n");
        result.append("- Username: ").append(admin.getUserName())
                .append("\n- Full Name: ").append(admin.getName()+admin.getLastname())
                .append("\n- Email: ").append(admin.getEmail())
                .append("\n- Phone Number: ").append(admin.getPhoneNumber())
                .append("\n");

        return result.toString();
    }











}
