package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class SingUpController {
    private static SingUpController instance;

    public SingUpController(){
        instance = this;
    }
    public static SingUpController getInstance() {
        return instance;
    }
    private DirectoryChooser directoryChooser;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    public TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phonenumber;

    @FXML
    private ImageView image;

    @FXML
    public TextField username;

    @FXML
    private Button select;

    public String url;

    @FXML
    void select(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage)select.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        url=selectedFile.getAbsolutePath();
        Image images = new Image(selectedFile.toURI().toString());
        image.setImage(images);




    }




    @FXML
    void Singup(ActionEvent event) {
        String string=UserController.getUserController().singUp(username.getText(),password.getText(),name.getText(),lastname.getText(),email.getText(),phonenumber.getText(),url);
       if (string.equals("This username and password are duplicates.")){
           username.clear();
           password.clear();
           Alert alert = new Alert(AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("This username and password are duplicates.");
           alert.showAndWait();
       }
       else {
           UserController.getUserController().login(username.getText(),password.getText());
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Registration was successful!");
           alert.showAndWait();
           MainPage.stage.setScene(MainPage.categoryScene);
           MainPage.stage.show();
           username.clear();
           phonenumber.clear();
           password.clear();
           name.clear();
           email.clear();
           lastname.clear();
           image.setImage(null);
       }



    }

}
