package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button singUp;

    @FXML
    private TextField username;

    @FXML
    void Login(ActionEvent event) {
        String result=UserController.getUserController().login(username.getText(),password.getText());
      if (result.equals("this user is banned")){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("this user is banned");
          alert.showAndWait();
          username.clear();
          password.clear();
      }
      else if (result.equals("Admin login successful.")) {
        MainPage.stage.setScene(MainPage.admin);
        MainPage.stage.show();
        username.clear();
        password.clear();
        }
      else if (result.equals("The username or password is incorrect.")){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("The username or password is incorrect.\n Enter the sign-up page by clicking the sign-up button and sign up.");
          alert.showAndWait();
          username.clear();
          password.clear();
      }

      else if (result.equals("The entry was successful.")){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("The entry was successful.");
          alert.showAndWait();
          MainPage.stage.setScene(MainPage.libraryScene);
          MainPage.stage.show();
          username.clear();
          password.clear();
      }
      else{
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("Enter the sign-up page by clicking the sign-up button and sign up.");
          alert.showAndWait();
          username.clear();
          password.clear();
      }



    }

    @FXML
    void SingUp(ActionEvent event) {
        MainPage.stage.setScene(MainPage.singUpScene);
        MainPage.stage.show();
        username.clear();
        password.clear();
    }

}
