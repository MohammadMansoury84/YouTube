package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibraryController {
    public static Stage playlistStage;
    @FXML
    public ListView<String> playList;

    private static LibraryController instance;

    public LibraryController() {
        instance = this;
    }
    public static LibraryController getInstance() {
        return instance;
    }

    @FXML
    public void initialize (){
        playList.getItems().add("Liked");
        playList.getItems().add("Watch Later");
        playList.setOnMouseClicked(mouseEvent -> {
            String item=playList.getSelectionModel().getSelectedItem();
            processPlayList();
        });
    }
    void processPlayList(){
        playlistStage=new Stage();
        playlistStage.setScene(MainPage.contentToPlaylist);
        playlistStage.initModality(Modality.APPLICATION_MODAL);
        playlistStage.show();
    }




    @FXML
    void CreateNewPlaylist(ActionEvent event) {
        MainPage.stage.setScene(MainPage.createPlayListScene);
        MainPage.stage.show();

    }

    @FXML
    void getPeremium(ActionEvent event) {
        MainPage.stage.setScene(MainPage.premium);
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
    void viewAccountInformation(ActionEvent event) {
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();

    }



}
