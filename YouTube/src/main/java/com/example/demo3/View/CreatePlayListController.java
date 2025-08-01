package com.example.demo3.View;

import com.example.demo3.Controller.PlayListController;
import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CreatePlayListController {
    private int id=1;
    private int num=1;
    String type;

    @FXML
    private ToggleGroup mm;

    @FXML
    private TextField name;

    @FXML
    private RadioButton user;

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();

    }
    int makeId(){
        return id++;
    }


    @FXML
    void createPlaylist(ActionEvent event) {
        if (user.isSelected()) {
            type = "U";
        }
        String result = UserController.getUserController().CreatePlaylist(type, name.getText());
        if (!premiumController.isPremium) {
            if (num <= 3) {
                if (result.equals("Playlist was created successfully.")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Playlist was created successfully.");
                    LibraryController.getInstance().playList.getItems().add(String.valueOf(makeId()) + "-" + name.getText());
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

