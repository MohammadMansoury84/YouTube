package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReportController {

    @FXML
    private TextField contentId;

    @FXML
    private TextArea explanation;

    @FXML
    void add(ActionEvent event) {
       String result= UserController.getUserController().reportContent(Integer.parseInt(contentId.getText()),explanation.getText());
       if (result.equals("Content not found with ID")) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Content not found with ID");
           alert.show();
           explanation.clear();
           contentId.clear();
       }
       else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Report submitted for content");
           alert.show();
           explanation.clear();
           contentId.clear();
       }

    }

}
