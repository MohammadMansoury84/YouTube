package com.example.demo3.Controller;




import com.example.demo3.Model.*;
import com.example.demo3.Model.Account.*;
import com.example.demo3.Model.Content.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UserController {

    private static UserController userController;

    private UserController() {
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,16}$";
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";





    public Database database = Database.getDatabase();
    public PremiumPack premiumPack;
    public Account account;
    public Channel channel;
    public Admin admin = Admin.getAdmin();
    public User user = new NormalUser( "mamad","1234","mamady","mansoury","mm","123456",null) ;


    private int numOfUserId = 0;
    private int numOfReportId = 0;
    private int numOfCommentId = 0;


    public int generateUniqueId() {
        numOfUserId++;
        return numOfUserId;
    }

    public int generateUniqueReportId() {
        numOfReportId++;
        return numOfReportId;
    }

    public int generateUniqueCommentId() {
        numOfCommentId++;
        return numOfCommentId;
    }

    public String singUp(String username, String password, String name, String lastname, String email, String phoneNumber, String profileCover) {
        if (database.getDatabase().getUsers().stream().anyMatch(user -> user.getUserName().equals(username))) {
            return "This username and password are duplicates.";
        }

        User newUser = new NormalUser(username, password, name, lastname, email, phoneNumber, profileCover);
        user=newUser;
        newUser.setId(generateUniqueId());
        database.getDatabase().getUsers().add(newUser);
        return "Sign up successfully.";
    }

    public String login(String username, String password) {
        if (!user.isBanned()) {
            if (admin.getAdmin().getUserName().equals(username) && admin.getAdmin().getPassword().equals(password)) {
                System.out.println("Admin login successful.");
                return "Admin login successful.";
            } else {
                for (Account account : database.getDatabase().getUsers()) {
                    if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                        user = (User) account;
                        ChannelController.getChannelController(user);
                        AdminController.getAdminController();
                        PlayListController.getPlayListController(user);



                        return "The entry was successful.";
                    }
                }
                user = null;
                return "The username or password is incorrect.";
            }
        }
        else return "this user is banned";
    }
    public String logout() {
        if (user == null) {
            return "No user is currently logged in.";
        }
        ArrayList arrayList=null;
        user.setFavoriteCategories(arrayList);
        String username = user.getUserName();
        return "User '" + username + "' logged out successfully.";
    }

    public String selectFavouriteCategories(String input) {

        String[] categories = input.split(",");

        for (String category : categories) {
            category = category.trim();
            if (Category.isValid(category)) {
                user.getFavoriteCategories().add(Category.valueOf(category.toUpperCase()));
            } else {
                return "Invalid category: " + category + "\n" + "Successfully selected: " + user.getFavoriteCategories().toString();
            }
        }
        return "Successfully selected";

    }



    public String AccountInfo() {
        return "Username: " + user.getUserName() + "\n" +
                "Password: "+user.getPassword()+ "\n" +
                "credit: "+user.getCredit()+ "\n" +
                "Full Name: " + user.getName() + user.getLastname() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Phone: " + user.getPhoneNumber() + "\n"+
                "Favourite Category: "+user.getFavoriteCategories().toString();


    }

    public String editUserInfo(String editType, String nameOrPassword) {

        if (editType.equals("N")) {
            user.setName(nameOrPassword);
            return "Name successfully updated" ;
        } else if (editType.equals("P")) {
            user.setPassword(nameOrPassword);
            return "Password successfully updated";
        } else {
            return "Invalid option. Use 'N' for name or 'P' for password.";
        }
    }

    public String playContent(int contentID) {

        Content content = null;
        for (Content c : database.getDatabase().getContents()) {
            if (c.getID() == contentID) {
                content = c;
                break;
            }
        }
        if (content == null) {
            return "Content not found with ID: " + contentID;
        }
        if (content.getIsExclusive() && user instanceof NormalUser) {
            return "Access denied. Premium content requires a premium account.";
        }

        content.setViews(content.getViews() + 1);
        return null;
    }

    public String likeContent(int contentID) {
        Content content = null;
        for (Content c : database.getDatabase().getContents()) {
            if (c.getID() == contentID) {
                content = c;
                break;
            }
        }
        if (content == null) {
            return "Content not found with ID: " + contentID;
        }
        content.setLikes(content.getLikes() + 1);
        return null;
    }
    public String disLikeContent(int contentID) {
        Content content = null;
        for (Content c : database.getDatabase().getContents()) {
            if (c.getID() == contentID) {
                content = c;
                break;
            }
        }
        if (content == null) {
            return "Content not found with ID: " + contentID;
        }
        content.setLikes(content.getLikes() - 1);
        return null;
    }

    public String reportContent(int contentID, String explanation) {
        Content content = null;
        for (Content c : database.getDatabase().getContents()) {
            if (c.getID() == contentID) {
                content = c;
                break;
            }
        }
        if (content == null) {
            return "Content not found with ID";
        }
        Report report = new Report(contentID, explanation);
        database.getDatabase().getReports().add(report);
        report.setId(generateUniqueReportId());
        report.setReporter(user);
        return "Report submitted for content";

    }

    public String search(String query) {
        StringBuilder results = new StringBuilder();
        for (Channel channel : database.getDatabase().getChannels()) {
            if (channel.getName().toLowerCase().contains(query.toLowerCase())) {
                results.append("Channel Found: ").append(channel.getName()).append("\n");
            }
            else {return "Channel Not Found";}

        }
        for (Content content : database.getDatabase().getContents()) {
            if (content.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.append("Content Found: ").append(content.getTitle()).append("\n");
            }
            else {return "Content Not Found";}
        }
        return "";
    }
    public String addCredit(int amount) {
        user.setCredit(user.getCredit()+ amount);
        return "Credit added successfully";
    }
    public String addCommentToContent(int contentID, String commentText) {
        Content content = null;
        for (Content c : database.getDatabase().getContents()) {
            if (c.getID() == contentID) {
                content = c;
                break;
            }
        }
        if (content == null) {
            return "Content not found with ID: " + contentID;
        }
        Comment comment = new Comment(commentText, LocalDateTime.now());
        comment.setCommentId(generateUniqueCommentId());
        comment.setCommenter(user);
        content.getComments().add(comment);
        return "Comment added successfully to content: " + content.getTitle();
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
    public String filter(String filterType) {
        if (filterType.equals("V")) {
            ArrayList<Content> videos = new ArrayList<>();
            for (Content content : database.getDatabase().getContents()) {
                if (content instanceof Video) {
                    videos.add(content);
                }
            }
            if (videos.isEmpty()) {
                return "No video content found in the database.";
            }
            StringBuilder result = new StringBuilder("List of All Video Contents:\n");
            for (Content content : videos) {
                result.append("- Title: ").append(content.getTitle())
                        .append("\n  Likes: ").append(content.getLikes())
                        .append("\n  Views: ").append(content.getViews())
                        .append("\n  Description: ").append(content.getDescription())
                        .append("\n\n");
            }
            return result.toString();
        } else if (filterType.equals("P")) {
            ArrayList<Content> podcasts = new ArrayList<>();
            for (Content content : database.getContents()) {
                if (content instanceof Podcast) {
                    podcasts.add(content);
                }
            }
            if (podcasts.isEmpty()) {
                return "No video content found in the database.";
            }
            StringBuilder result = new StringBuilder("List of All Video Contents:\n");
            for (Content content : podcasts) {
                result.append("- Title: ").append(content.getTitle())
                        .append("\n  Likes: ").append(content.getLikes())
                        .append("\n  Views: ").append(content.getViews())
                        .append("\n  Description: ").append(content.getDescription())
                        .append("\n\n");
            }

        }
        return null;
    }

    public String filter(String filterType,String filterBy) {
        if (filterType.equals("C")) {
            ArrayList<Content> filteredContents = new ArrayList<>();
            for (Content content : database.getDatabase().getContents()) {
                if (content.getCategory() == Category.valueOf(filterBy)) {
                    filteredContents.add(content);
                }
            }
            if (filteredContents.isEmpty()) {
                return "No content found in the category: " + filterBy;
            }

            StringBuilder result = new StringBuilder("Filtered Contents in Category: " + filterBy + "\n");
            for (Content content : filteredContents) {
                result.append("- Title: ").append(content.getTitle())
                        .append("\n  Likes: ").append(content.getLikes())
                        .append("\n  Views: ").append(content.getViews())
                        .append("\n  Description: ").append(content.getDescription())
                        .append("\n\n");
            }

            return result.toString();
        }
        return "";
    }
    public String sortContents(String sortBy) {
        ArrayList<Content> contents = new ArrayList<>(database.getDatabase().getContents());
        if (sortBy.equalsIgnoreCase("L")) {
            contents.sort(Comparator.comparingInt(Content::getLikes).reversed());
        } else if (sortBy.equalsIgnoreCase("V")) {
            contents.sort(Comparator.comparingInt(Content::getViews).reversed());
        }
        if (contents.isEmpty()) {
            return "No contents available in the database.";
        }
        StringBuilder result = new StringBuilder("Sorted Contents by ");
        result.append(sortBy.equalsIgnoreCase("L") ? "Likes" : "Views").append(":\n");
        for (Content content : contents) {
            result.append("- Title: ").append(content.getTitle())
                    .append("\n  Likes: ").append(content.getLikes())
                    .append("\n  Views: ").append(content.getViews())
                    .append("\n  Description: ").append(content.getDescription())
                    .append("\n\n");
        }

        return result.toString();
    }
    public ArrayList<Content> getSuggestedContents() {
        ArrayList<Content> suggestions = new ArrayList<>();


        for (Category category : user.getFavoriteCategories()) {
            for (Content content : database.getDatabase().getContents()) {
                if (content.getCategory() == category && !suggestions.contains(content)) {
                    suggestions.add(content);
                }
            }
        }

        for (Channel channel : user.getSubscriptions()) {
            for (Playlist playlist : channel.getPlaylists()) {
                for (Content content : playlist.getContents()) {
                    if (!suggestions.contains(content)) {
                        suggestions.add(content);
                    }
                }
            }
        }
        return new ArrayList<>(suggestions.subList(0, Math.min(10, suggestions.size())));
    }
    public String viewPlaylistsAndContents() {
        Channel channel = user.getChannel();
        if (channel == null || channel.getPlaylists().isEmpty()) {
            return "No playlists found for this user.";
        }
        StringBuilder result = new StringBuilder("Playlists and their Contents:\n");
        for (Playlist playlist : channel.getPlaylists()) {
            result.append("Playlist Name: ").append(playlist.getName()).append("\n");

            if (playlist.getContents().isEmpty()) {
                result.append("  No contents in this playlist.\n\n");
            } else {
                for (Content content : playlist.getContents()) {
                    result.append("  - Title: ").append(content.getTitle())
                            .append("\n    Description: ").append(content.getDescription())
                            .append("\n    Likes: ").append(content.getLikes())
                            .append("\n    Views: ").append(content.getViews())
                            .append("\n\n");
                }
            }
        }

        return result.toString();
    }
    public String viewSubscribedChannels() {
        ArrayList<Channel> subscriptions = user.getSubscriptions();
        if (subscriptions == null || subscriptions.isEmpty()) {
            return "No subscribed channels found for this user.";
        }
        StringBuilder result = new StringBuilder("List of Subscribed Channels:\n");
        for (Channel channel : subscriptions) {
            result.append("- Channel Name: ").append(channel.getName())
                    .append("\n  Total Playlists: ").append(channel.getPlaylists().size())
                    .append("\n  Total Followers: ").append(channel.getSubscribers().size())
                    .append("\n\n");
        }
        return result.toString();
    }
    public String viewAllChannels() {
        ArrayList<Channel> channels = database.getDatabase().getChannels();
        if (channels == null || channels.isEmpty()) {
            return "No channels found in the database.";
        }
        StringBuilder result = new StringBuilder("List of All Channels:\n");
        for (Channel channel : channels) {
            result.append("- Channel Name: ").append(channel.getName())
                    .append("\n  Total Playlists: ").append(channel.getPlaylists().size())
                    .append("\n  Total Followers: ").append(channel.getSubscribers().size())
                    .append("\n  Created By: ").append(channel.getOwnerName())
                    .append("\n\n");
        }
        return result.toString();
    }
    public String showChannelContents(int channelID) {
        Channel channel = null;
        for (Channel c : database.getDatabase().getChannels()) {
            if (c.getId() == channelID) {
                channel = c;
                break;
            }
        }
        if (channel == null) {
            return "Channel with ID " + channelID + " not found.";
        }
        StringBuilder result = new StringBuilder("Contents of Channel: " + channel.getName() + "\n");
        if (channel.getPlaylists().isEmpty()) {
            result.append("No playlists found in this channel.\n");
        } else {
            for (Playlist playlist : channel.getPlaylists()) {
                result.append("\nPlaylist Name: ").append(playlist.getName()).append("\n");
                if (playlist.getContents().isEmpty()) {
                    result.append("  No contents in this playlist.\n");
                } else {
                    for (Content content : playlist.getContents()) {
                        result.append("  - Title: ").append(content.getTitle())
                                .append("\n    Description: ").append(content.getDescription())
                                .append("\n    Likes: ").append(content.getLikes())
                                .append("\n    Views: ").append(content.getViews())
                                .append("\n\n");
                    }
                }
            }
        }
        return result.toString();
    }
    public String purchasePremium( String packageType) {
        try {
        PremiumPack premiumPack ;
        premiumPack = PremiumPack.valueOf(packageType.toUpperCase());
        if (user.getCredit() < premiumPack.getPrice()) {
            return "Insufficient credit.";
        }
        user.setCredit(user.getCredit() - premiumPack.getPrice());
        if (user instanceof NormalUser) {
            PremiumUser premiumUser = convertToPremium((NormalUser) user);
            database.getDatabase().getUsers().remove(user);
            database.getDatabase().getUsers().add(premiumUser);
            user = premiumUser;
            user.setPremium(true);
        }
        PremiumUser premiumUser = (PremiumUser) user;
        return "Premium package purchased successfully!";
        } catch (IllegalArgumentException e) {
            return "The selected package is not valid! Available options: BRONZE, SILVER, GOLD";
        }
    }
    private PremiumUser convertToPremium(NormalUser normalUser) {
        PremiumUser premiumUser = new PremiumUser(
                normalUser.getUserName(),
                normalUser.getPassword(),
                normalUser.getName(),
                normalUser.getLastname(),
                normalUser.getEmail(),
                normalUser.getPhoneNumber(),
                normalUser.getProfileCover(),
                normalUser.getCredit()
        );
        premiumUser.setCredit(normalUser.getCredit());
        premiumUser.setChannel(normalUser.getChannel());
        premiumUser.getSubscriptions().addAll(normalUser.getSubscriptions());
        premiumUser.getFavoriteCategories().addAll(normalUser.getFavoriteCategories());
        premiumUser.getPlaylists().addAll(normalUser.getPlaylists());

        return premiumUser;
    }








        public ArrayList<Content> allContents;

        public NormalUser normalUser;
        private static com.example.demo3.Controller.ChannelController channelController;
        public Content content;
        public int numOfChannel = 0;
        public int numOfPlaylist = 0;
        public int unmOfContent = 0;





        public String createChannel(String name, String description, String cover) {
            Channel channel1 = new Channel(name, description, cover);
            channel=channel1;
            user.setChannel(channel);
            channel.setOwnerName(user.getUserName());
            channel.setId(generateUniqueChannelId());
            channel.setOwnerName(user.getUserName());
            database.getDatabase().getChannels().add(channel);
            return "Channel was created successfully.";
        }

        public int generateUniqueChannelId() {
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
            normalUser=(NormalUser) user;
            if (channelOrUser.equals("C")) {
                Playlist playlist = new Playlist(name);
                playlist.setId(generateUniquePlayListId());

                user.getPlaylists().add(playlist);
                return "Playlist was created successfully.";
            }
            else {
                if (user instanceof NormalUser) {
                    if (user.getPlaylists().size() <=3) {
                        Playlist playlist = new Playlist(name);
                        playlist.setId(generateUniquePlayListId());
                        user.getPlaylists().add(playlist);
                        return "Playlist was created successfully.";
                    } else return "Normal user cannot create more than normalUser MaxPlayList playlists.";
                } else {
                    Playlist playlist = new Playlist(name);
                    playlist.setId(generateUniquePlayListId());
                    channel.getPlaylists().add(playlist);
                    return "Playlist was created successfully.";
                }

            }
        }

        public String AddContentToPlaylist(int playlistId, int contentId) {
            Content targetContent=  null;
            Database db = Database.getDatabase().getDatabase();
            for (Content content : db.getDatabase().getContents()) {
                if (content.getID() == contentId) {
                    targetContent = content;
                    break;
                }
            }
            if (targetContent == null) {
                return "Content with ID not found.";
            }
            Playlist targetPlaylist = null;
            for (Playlist playlist : user.getPlaylists()) {
                if (playlist.getId() == playlistId) {
                    targetPlaylist = playlist;
                    break;
                }
            }
            if (targetPlaylist == null) {
                return "Playlist with ID not found for the user.";
            }
            if (user instanceof NormalUser) {
                NormalUser normalUser = (NormalUser) user;
                if (targetPlaylist.getContents().size() <= normalUser.getMaxContentsPerPl()) {
                    targetPlaylist.getContents().add(targetContent);
                } else
                    return "Normal user cannot add more than contents to a playlist.";
            } else {
                targetPlaylist.getContents().add(targetContent);
            }

            return "Content was added successfully to playlist .";
        }


        public String PublishPodcast( String specialOrRegular, String title, String description, String duration, String category, String contentLink, String cover, String podcaster) {
            Podcast podcast = new Podcast(title, description, duration, contentLink, cover, podcaster);
            if (user.getChannel() == null || user.getChannel().getPlaylists().isEmpty()) {
                return "Error: User does not have a channel or playlists.";
            }
            else {
                user.getChannel().getContents().add(podcast);
                user.getChannel().getPlaylists().get(0).addContent(podcast);
                database.getDatabase().getContents().add(podcast);
                podcast.setExclusive(specialOrRegular.equals("Y"));
                podcast.setID(generateUniqueContentId());
                podcast.setCategory(Category.valueOf(category));
                return "published successfully";
            }
            
        }

        public String PublishNormalVideo( String specialOrRegular, String title, String description, String duration, String category, String contentLink, String cover, String subtitle,String quality,String format) {
            NormalVideo normalVideo = new NormalVideo(title, description, duration, contentLink, cover, subtitle);
            if (user.getChannel() == null || user.getChannel().getPlaylists().isEmpty()) {
                return "Error: User does not have a channel or playlists.";
            }
            else {
                user.getChannel().getPlaylists().get(0).addContent(normalVideo);
                user.getChannel().getContents().add(normalVideo);
                database.getDatabase().getContents().add(normalVideo);
                normalVideo.setExclusive(specialOrRegular.equals("Y"));
                normalVideo.setID(generateUniqueContentId());
                normalVideo.setCategory(Category.valueOf(category));
                normalVideo.setFormat(VideoFormat.valueOf(format));
                normalVideo.setQuality(VideoQuality.valueOf("Q" + quality));
                return "published successfully";
            }
        }




}