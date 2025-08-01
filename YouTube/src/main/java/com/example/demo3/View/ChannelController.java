package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChannelController {

    @FXML
    void create(ActionEvent event) {
        MainPage.stage.setScene(MainPage.createChannel);
        MainPage.stage.show();

    }

    @FXML
    void goToChannel(ActionEvent event) {
        MainPage.stage.setScene(MainPage.channel);
        MainPage.stage.show();

    }

    @FXML
    void goToHome(ActionEvent event) {
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();
    }

    @FXML
    void goToLibrary(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();

    }

    @FXML
    void goToProfile(ActionEvent event) {
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();

    }

    @FXML
    void goToSubscription(ActionEvent event) {
        MainPage.stage.setScene(MainPage.subscription);
        MainPage.stage.show();
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

    @FXML
    void playList(ActionEvent event) {
        MainPage.stage.setScene(MainPage.channelPlayList);
        MainPage.stage.show();

    }

    @FXML
    void view(ActionEvent event) {
        MainPage.stage.setScene(MainPage.informationChannel);
        MainPage.stage.show();
    }

}
