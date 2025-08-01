package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EditInformationUserController {

    @FXML
    private ToggleGroup bb;

    @FXML
    private RadioButton name;

    @FXML
    private TextField nameOrPassword;

    @FXML
    private RadioButton password;
    private String editType;

    @FXML
    void name(ActionEvent event) {
        if (name.isSelected()){
            editType="N";
        }
    }
    @FXML
    void password(ActionEvent event) {
        if (password.isSelected()){
            editType="P";
        }
    }
    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();
    }

    @FXML
    void confirmation(ActionEvent event) {
        String result=UserController.getUserController().editUserInfo(editType,nameOrPassword.getText());
        if (result.equals("Name successfully updated")){
            showAlert("Name successfully updated");
        }
        else if(result.equals("Password successfully updated")){
            showAlert("Password successfully updated");
        }
        else {
            showAlert("Invalid option. Use 'N' for name or 'P' for password.");
        }

    }
    void showAlert(String massage){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }

}
