package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CommentController {
    private static CommentController instance;

    public CommentController(){
        instance = this;
    }
    public static CommentController getInstance() {
        return instance;
    }

    @FXML
    private TextArea comment;

    @FXML
    private TextField contentId;

    @FXML
    void add(ActionEvent event) {
        UserController.getUserController().addCommentToContent(Integer.parseInt(contentId.getText()),comment.getText());
        PlayVideoController.getInstance().comment.getItems().add(SingUpController.getInstance().username.getText()+"_"+comment.getText());
        comment.clear();
        contentId.clear();
    }

}
