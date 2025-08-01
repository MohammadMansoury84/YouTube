package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddContentToPlaylistController {
    @FXML
    private ListView<String> content;

    @FXML
    private TextField ContentID;

    @FXML
    private TextField playListID;

    @FXML
    void add(ActionEvent event) {
      String result=UserController.getUserController().AddContentToPlaylist(Integer.parseInt(playListID.getText()),Integer.parseInt(ContentID.getText()));
      if (result.equals("Content with ID not found.")){
          showAlert("Content with ID not found.");
      }
      else if(result.equals("Playlist with ID not found for the user.")){
          showAlert("Playlist with ID not found for the user.");
      }
      else if(result.equals("Normal user cannot add more than contents to a playlist.")){
          showAlert("Normal user cannot add more than contents to a playlist.");
      }
      else {
          Alert alert =new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Content was added successfully to playlist .");
      }
        Content targetContent=  null;
        Database db = Database.getDatabase().getDatabase();
        for (Content content : db.getDatabase().getContents()) {
            if (content.getID() == Integer.parseInt(ContentID.getText())) {
                targetContent = content;
                break;
            }
        }
        if (targetContent == null) {
            showAlert("Content with ID not found.");
        }

      content.getItems().add(targetContent.getTitle());
    }

    public void initialize (){

        content.setOnMouseClicked(mouseEvent -> {
            String item=content.getSelectionModel().getSelectedItem();
            processPlayList(item);
        });
    }
    void processPlayList(String item) {
        Content targetContent = null;
        Database db = Database.getDatabase().getDatabase();

        for (Content content : db.getDatabase().getContents()) {
            if (content.getTitle().equals(targetContent.getTitle())) {
                targetContent = content;
                break;
            }
        }

        if (targetContent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Content with ID not found.");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        PlayVideoController.getInstance().getUrlContent(targetContent.getLink());
        UserController.getUserController().playContent(targetContent.getID());
        MainPage.stage.setScene(MainPage.playVideo);
        MainPage.stage.show();

    }
    void showAlert(String massage){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }


}