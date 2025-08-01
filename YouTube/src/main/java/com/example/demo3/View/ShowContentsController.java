package com.example.demo3.View;

import com.example.demo3.MainPage;
import com.example.demo3.Model.Comment;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ShowContentsController {
    private static ShowContentsController instance;

    public ShowContentsController(){
        instance = this;
    }
    public static ShowContentsController getInstance() {
        return instance;
    }

    @FXML
    public ListView<String> contentList;

    @FXML
    void back(ActionEvent event) {
        contentList.getItems().clear();
        MainPage.stage.setScene(MainPage.admin);
        MainPage.stage.show();
    }
    public void initialize () {

        contentList.setOnMouseClicked(mouseEvent -> {
           String item= contentList.getSelectionModel().getSelectedItem();
           Content targetContent=null;
            Database database=Database.getDatabase();
            for (Content content:database.getDatabase().getContents()){
                if (content.getTitle().equals(item)){
                    ContentInfoController.getInstance().likes.setText(String.valueOf(content.getLikes()));
                    ContentInfoController.getInstance().name.setText(content.getTitle());
                    ContentInfoController.getInstance().views.setText(String.valueOf(content.getViews()));
                    for (Comment comment : content.getComments()){
                        ContentInfoController.getInstance().comentList.getItems().add("user:"+comment.getCommenter().getUserName()+" | "+"comment:"+comment.getComment());
                    }
                    ContentInfoController.getInstance().initialize(content.getLink());
                    MainPage.stage.setScene(MainPage.contentInfo);
                    MainPage.stage.show();
                }
            }

        });
    }

}
