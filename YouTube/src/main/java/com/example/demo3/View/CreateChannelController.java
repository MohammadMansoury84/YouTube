package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateChannelController {

    @FXML
    private TextArea description;

    @FXML
    private TextField name;

    @FXML
    private ImageView image;
    @FXML
    private Button choose;

    private String url;

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.channel);
        MainPage.stage.show();

    }

    @FXML
    void choose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage)choose.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        url=selectedFile.getAbsolutePath();
        Image images = new Image(selectedFile.toURI().toString());
        image.setImage(images);

    }

    @FXML
    void create(ActionEvent event) {
        String result = UserController.getUserController().createChannel(name.getText(),description.getText(),url);
        if (result.equals("Channel was created successfully.")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Channel was created successfully.");
            alert.show();
            name.clear();
            description.clear();
        }
        else {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("There is a problem,try again.");
            alert.show();
        }

    }

}
