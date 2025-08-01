package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage extends Application {
    public static Scene singUpScene;
    public static Scene categoryScene;
    public static Scene profileScene;
    public static Scene libraryScene;
    public static Scene loginScene;
    public static Scene createPlayListScene;
    public static Scene premium;
    public static Scene increaseBalance;
    public static Scene contentToPlaylist;
    public static Scene admin;
    public static Scene channel;
    public static Scene createChannel;
    public static Scene informationChannel;
    public static Scene channelPlayList;
    public static Scene addContentsToChannel;
    public static Scene editInformationUser;
    public static Scene playVideo;
    public static Scene comment;
    public static Scene report;
    public static Scene subscription;
    public static Scene showChannelInfo;
    public static Scene showContents;
    public static Scene showUsers;
    public static Scene showReports;
    public static Scene userInfo;
    public static Scene contentInfo;
    public static Scene home;
    public static Scene suggested;
    public static Scene playVideo2;
    public static Scene playVideo3;
    public static Scene charts;
    public static Stage stage;



    @Override
    public void start(Stage stage) throws IOException {
        MainPage.stage=stage;
        FXMLLoader SingUp = new FXMLLoader(MainPage.class.getResource("SingUp-view.fxml"));
        Scene scene_singUp = new Scene(SingUp.load(), 700, 700);
        singUpScene=scene_singUp;
        FXMLLoader Login = new FXMLLoader(MainPage.class.getResource("Login-view.fxml"));
        Scene scene_Login = new Scene(Login.load(), 700, 700);
        loginScene=scene_Login;
        FXMLLoader Category = new FXMLLoader(MainPage.class.getResource("Category-view.fxml"));
        Scene scene_Category = new Scene(Category.load(), 700, 700);
        categoryScene=scene_Category;
        FXMLLoader Profile = new FXMLLoader(MainPage.class.getResource("Profile-view.fxml"));
        Scene scene_Profile = new Scene(Profile.load(), 700, 700);
        profileScene=scene_Profile;
        FXMLLoader Library = new FXMLLoader(MainPage.class.getResource("Library-view.fxml"));
        Scene scene_Library = new Scene(Library.load(), 700, 700);
        libraryScene=scene_Library;
        FXMLLoader CreatePlayList = new FXMLLoader(MainPage.class.getResource("CreatePlayList-view.fxml"));
        Scene scene_CreatePlayList = new Scene(CreatePlayList.load(), 700, 700);
        createPlayListScene=scene_CreatePlayList;
        FXMLLoader  Premium = new FXMLLoader(MainPage.class.getResource("premium-view.fxml"));
        Scene scene_Premium= new Scene(Premium.load(), 700, 700);
        premium=scene_Premium;
        FXMLLoader  IncreaseBalance = new FXMLLoader(MainPage.class.getResource("IncreaseBalance-view.fxml"));
        Scene scene_IncreaseBalance= new Scene(IncreaseBalance.load(), 400, 400);
        increaseBalance=scene_IncreaseBalance;
        FXMLLoader  Admin = new FXMLLoader(MainPage.class.getResource("Admin-view.fxml"));
        Scene scene_Admin= new Scene(Admin.load(), 700, 700);
        admin=scene_Admin;
        FXMLLoader  AddContentToPlaylist = new FXMLLoader(MainPage.class.getResource("AddContentToPlaylist-view.fxml"));
        Scene scene_AddContentToPlaylist= new Scene(AddContentToPlaylist.load(), 700, 700);
        contentToPlaylist=scene_AddContentToPlaylist;
        FXMLLoader  Channel = new FXMLLoader(MainPage.class.getResource("Channel-view.fxml"));
        Scene scene_Channel= new Scene(Channel.load(), 700, 700);
        channel=scene_Channel;
        FXMLLoader  CreateChannel = new FXMLLoader(MainPage.class.getResource("CreateChannel-view.fxml"));
        Scene scene_CreateChannel= new Scene(CreateChannel.load(), 700, 700);
        createChannel=scene_CreateChannel;
        FXMLLoader  InformationChannel = new FXMLLoader(MainPage.class.getResource("InformationChannel-view.fxml"));
        Scene scene_InformationChannel= new Scene(InformationChannel.load(), 700, 700);
        informationChannel=scene_InformationChannel;
        FXMLLoader  ChannelPlayList = new FXMLLoader(MainPage.class.getResource("ChannelPlayList-view.fxml"));
        Scene scene_ChannelPlayList= new Scene(ChannelPlayList.load(), 700, 700);
        channelPlayList=scene_ChannelPlayList;
        FXMLLoader AddContentsToChannel = new FXMLLoader(MainPage.class.getResource("AddContentsToChannel-view.fxml"));
        Scene scene_addContentsToChannel= new Scene(AddContentsToChannel.load(), 700, 700);
        addContentsToChannel=scene_addContentsToChannel;
        FXMLLoader EditInformationUser = new FXMLLoader(MainPage.class.getResource("EditInformationUser-view.fxml"));
        Scene scene_editInformationUser= new Scene(EditInformationUser.load(), 700, 700);
        editInformationUser=scene_editInformationUser;
        FXMLLoader PlayVideo = new FXMLLoader(MainPage.class.getResource("PlayVideo-view.fxml"));
        Scene scene_PlayVideo= new Scene(PlayVideo.load(), 700, 700);
        playVideo=scene_PlayVideo;
        FXMLLoader Comment = new FXMLLoader(MainPage.class.getResource("Comment-view.fxml"));
        Scene scene_Comment= new Scene(Comment.load(), 400, 200);
        comment=scene_Comment;
        FXMLLoader Report = new FXMLLoader(MainPage.class.getResource("Report-view.fxml"));
        Scene scene_Report= new Scene(Report.load(), 400, 200);
        report=scene_Report;
        FXMLLoader Subscription = new FXMLLoader(MainPage.class.getResource("Subscription-view.fxml"));
        Scene Scene_Subscription= new Scene(Subscription.load(), 700, 700);
        subscription=Scene_Subscription;
        FXMLLoader ShowChannelInfo = new FXMLLoader(MainPage.class.getResource("ShowChannelInfo-view.fxml"));
        Scene Scene_ShowChannelInfo= new Scene( ShowChannelInfo.load(), 700, 700);
        showChannelInfo=Scene_ShowChannelInfo;
        FXMLLoader ShowContents = new FXMLLoader(MainPage.class.getResource("ShowContents-view.fxml"));
        Scene Scene_ShowContents= new Scene( ShowContents.load(), 700, 700);
        showContents=Scene_ShowContents;
        FXMLLoader ShowUsers = new FXMLLoader(MainPage.class.getResource("ShowUsers-view.fxml"));
        Scene Scene_ShowUsers= new Scene( ShowUsers.load(), 700, 700);
        showUsers=Scene_ShowUsers;
        FXMLLoader ShowReports = new FXMLLoader(MainPage.class.getResource("ShowReports-view.fxml"));
        Scene Scene_ShowReports = new Scene( ShowReports.load(), 700, 700);
        showReports= Scene_ShowReports;
        FXMLLoader UserInfo = new FXMLLoader(MainPage.class.getResource("UserInfo-view.fxml"));
        Scene Scene_UserInfo = new Scene( UserInfo.load(), 700, 700);
        userInfo= Scene_UserInfo;
        FXMLLoader ContentInfo = new FXMLLoader(MainPage.class.getResource("ContentInfo-view.fxml"));
        Scene Scene_ContentInfo = new Scene( ContentInfo.load(), 700, 700);
       contentInfo= Scene_ContentInfo;
        FXMLLoader Home = new FXMLLoader(MainPage.class.getResource("Home-view.fxml"));
        Scene Scene_Home= new Scene( Home.load(), 700, 700);
        home=Scene_Home;
        FXMLLoader Suggested = new FXMLLoader(MainPage.class.getResource("Suggested-view.fxml"));
        Scene Scene_Suggested = new Scene(Suggested.load(), 700, 700);
        suggested=Scene_Suggested;
        FXMLLoader PlayVideo2 = new FXMLLoader(MainPage.class.getResource("PlayVideo2-view.fxml"));
        Scene Scene_PlayVideo2 = new Scene(PlayVideo2.load(), 700, 700);
        playVideo2=Scene_PlayVideo2;
        FXMLLoader PlayVideo3 = new FXMLLoader(MainPage.class.getResource("PlayVideo3-view.fxml"));
        Scene Scene_PlayVideo3 = new Scene(PlayVideo3.load(), 700, 700);
        playVideo3=Scene_PlayVideo3;
        FXMLLoader Charts = new FXMLLoader(MainPage.class.getResource("Charts-view.fxml"));
        Scene Scene_Charts = new Scene(Charts.load(), 700, 700);
        charts=Scene_Charts;
        stage.setScene(scene_Login);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }
}