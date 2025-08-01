package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class premiumController {
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton bronze;
    @FXML
    private RadioButton gold;
    @FXML
    private RadioButton silver;
    private String  selectedPackage;
    public static boolean isPremium;
    public static boolean isBuy=false;


    @FXML
    void Increase(ActionEvent event) {
        MainPage.stage.setScene(MainPage.increaseBalance);
        MainPage.stage.show();

    }

    @FXML
    void purchase(ActionEvent event) {
        if (gold.isSelected()) {
            selectedPackage = "GOLD";
        } else if (silver.isSelected()) {
            selectedPackage = "SILVER";
        } else if (bronze.isSelected()) {
            selectedPackage = "BRONZE";
        } else {
            showAlert("Please choose a package!");
            return;
        }
        String result=UserController.getUserController().purchasePremium(selectedPackage);
        if (result.equals("Insufficient credit.")){
            showAlert("Insufficient credit.");
        }
        else {
            if (isBuy == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Premium package purchased successfully!");
                isPremium = true;
                isBuy=true;
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("You have already become premium and purchased a package.");
                isPremium = true;
                isBuy=true;
                alert.showAndWait();
            }
        }



    }

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();

    }
    void showAlert(String massage){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }

}
