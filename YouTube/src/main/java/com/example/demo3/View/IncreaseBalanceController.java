package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class IncreaseBalanceController {

    @FXML
    private TextField number;

    @FXML
    void pay(ActionEvent event) {
        String result = UserController.getUserController().addCredit(Integer.parseInt(number.getText()));
        Alert alert;
        if (result.equals("Credit added successfully")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Credit added successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Credit added Unsuccessfully");
            alert.showAndWait();
        }
        alert.setHeaderText(null);

    }
    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.premium);
        MainPage.stage.show();

    }


}
