package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ChannelPlayListController {

    @FXML
    private RadioButton channel;

    @FXML
    private ToggleGroup mm;

    @FXML
    private TextField name;

    private String type;
    private int id=1;
    private int num;

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.channel);
        MainPage.stage.show();
    }
    int makeId(){
        return id++;
    }

    @FXML
    void createPlaylist(ActionEvent event) {
        if (channel.isSelected()){
            type="C";
        }
        String result= UserController.getUserController().CreatePlaylist(type,name.getText());
        if (!premiumController.isPremium) {
            if (num <= 3) {
                if (result.equals("Playlist was created successfully.")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Playlist was created successfully.");
                    InformationChannelController.getInstance().playListList.getItems().add(String.valueOf(makeId())+"_"+name.getText());
                    name.clear();
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    num++;
                }
            } else {
                showAlert("Normal user cannot create more than normalUser MaxPlayList playlists.");
            }
        }
        else {
            if (result.equals("Playlist was created successfully.")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Playlist was created successfully.");
                LibraryController.getInstance().playList.getItems().add(String.valueOf(makeId()) + "-" + name.getText());
                name.clear();
                alert.setHeaderText(null);
                alert.showAndWait();

            }
        }
    }

    void showAlert(String massage){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }



}
