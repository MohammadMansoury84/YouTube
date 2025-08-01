package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Account.NormalUser;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class SubscriptionController {
    private static SubscriptionController instance;

    public SubscriptionController(){
        instance = this;
    }
    public static SubscriptionController getInstance() {
        return instance;
    }
    User user=new NormalUser(null,null,null,null,null,null,null);
    Channel channel=new Channel(null,null,null);

    @FXML
    public ListView<String> Subscriptions;

    @FXML
    void goToChannel(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();
        Subscriptions.getItems().clear();
    }

    @FXML
    void goToHome(ActionEvent event) {
        Subscriptions.getItems().clear();
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();

    }

    @FXML
    void goToLibrary(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();
        Subscriptions.getItems().clear();
    }

    @FXML
    void goToProfile(ActionEvent event) {
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();
        Subscriptions.getItems().clear();
    }

    @FXML
    void goToSubscription(ActionEvent event) {
        MainPage.stage.setScene(MainPage.subscription);
        MainPage.stage.show();
        initialize();
    }

    @FXML
    void logOut(ActionEvent event) {
        UserController.getUserController().logout();
        MainPage.stage.setScene(MainPage.loginScene);
        MainPage.stage.show();
        LibraryController.getInstance().playList.getItems().clear();
        InformationChannelController.getInstance().playListList.getItems().clear();
        AddContentsToChannelController.getInstance().contentList.getItems().clear();
        AddContentsToChannelController.getInstance().num=0;
    }

    public void initialize () {
        User targetUser = user;
        Database db = Database.getDatabase().getDatabase();

        for (User user : db.getDatabase().getUsers()) {
            if (user.getUserName().equals(SingUpController.getInstance().username.getText())) {
                targetUser = user;
                break;
            }
        }
        if (targetUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User not found.");
            alert.setHeaderText(null);
        }

        for (Channel channel:targetUser.getSubscriptions()){
            Subscriptions.getItems().add(channel.getName());
        }

        Subscriptions.setOnMouseClicked(mouseEvent -> {
            String item=Subscriptions.getSelectionModel().getSelectedItem();
            processSubscriptions(item);
        });


    }
    void processSubscriptions(String item){
        Channel targetChannel =channel;
        Database db = Database.getDatabase().getDatabase();
        for (Channel channel : db.getDatabase().getChannels()) {
            if (channel.getName().equals(item)) {
                targetChannel = channel;
                break;
            }
        }
        if (targetChannel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User not found.");
            alert.setHeaderText(null);
        }
        for (Content content : channel.getContents()){
            ShowChannelInfoController.getInstance().contents.getItems().add(content.getTitle());
        }
        ShowChannelInfoController.getInstance().getChannel(channel);
        ShowChannelInfoController.getInstance().subscribers.setText(String.valueOf(channel.getSubscribers().size()));
        ShowChannelInfoController.getInstance().channelName.setText(channel.getName());
        ShowChannelInfoController.getInstance().id.setText(String.valueOf(channel.getId()));
        ShowChannelInfoController.getInstance().description.setText(channel.getDescription());
        ShowChannelInfoController.getInstance().cover.setImage(new Image(channel.getCover()));
        MainPage.stage.setScene(MainPage.showChannelInfo);
        MainPage.stage.show();
    }



}
