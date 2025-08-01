package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class ProfileController {

    @FXML
    private Label info;

    @FXML
    private ImageView imageview;


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
        Image image = new Image(UserController.getUserController().user.getProfileCover());
        imageview.setImage(image);


        info.setText(UserController.getUserController().AccountInfo());
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();
    }


    @FXML
    void goToSubscription(ActionEvent event) {
        MainPage.stage.setScene(MainPage.subscription);
        MainPage.stage.show();
        SubscriptionController.getInstance().initialize();
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
        SubscriptionController.getInstance().Subscriptions.getItems().clear();

    }
    @FXML
    void edit(ActionEvent event) {
        MainPage.stage.setScene(MainPage.editInformationUser);
        MainPage.stage.show();

    }

}