package com.example.demo3.View;

import com.example.demo3.Controller.AdminController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UserInfoController {
    private static UserInfoController instance;

    public UserInfoController(){
        instance = this;
    }
    public static UserInfoController getInstance() {
        return instance;
    }

    @FXML
    public Text email;

    @FXML
    public Text id;

    @FXML
    public Text name;

    @FXML
    public Text password;

    @FXML
    public Text phoneNumber;

    @FXML
    public ImageView profile;

    @FXML
    public Text userName;

    @FXML
    void Ban(ActionEvent event) {
        String result =AdminController.getAdminController().banUser(Integer.parseInt(id.getText()));
        if (result.equals("User banned successfully")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("User banned successfully");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("User not found");
            alert.showAndWait();
        }

    }

    @FXML
    void Unban(ActionEvent event) {
        String result =AdminController.getAdminController().unbanUser(Integer.parseInt(id.getText()));
        if (result.equals("User is not banned")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("User is not banned");
            alert.showAndWait();
        }
        else if(result.equals("User unbanned successfully")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("User unbanned successfully");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("User not found");
            alert.showAndWait();
        }
    }

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.showUsers);
    }

}
