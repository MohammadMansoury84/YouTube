package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Comment;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SuggestedController {
    Content content1=new Content(null,null,null,null,null);
    private static SuggestedController instance;

    public SuggestedController(){
        instance = this;
    }
    public static SuggestedController getInstance() {
        return instance;
    }

    @FXML
    public ImageView imageView;

    @FXML
    public ImageView imageView1;

    @FXML
    public ImageView imageView2;

    @FXML
    public ImageView imageView3;

    @FXML
    public ImageView imageView4;

    @FXML
    public ImageView imageView5;

    @FXML
    public ImageView imageView6;

    @FXML
    public ImageView imageView7;

    @FXML
    public ImageView imageView8;

    @FXML
    public ImageView imageView9;

    @FXML
    public Text name;

    @FXML
    public Text name1;

    @FXML
    public Text name2;

    @FXML
    public Text name3;

    @FXML
    public Text name4;

    @FXML
    public Text name5;

    @FXML
    public Text name6;

    @FXML
    public Text name7;

    @FXML
    public Text name8;

    @FXML
    public Text name9;

    @FXML
    public Text time;

    @FXML
    public Text time1;

    @FXML
    public Text time2;

    @FXML
    public Text time3;

    @FXML
    public Text time4;

    @FXML
    public Text time5;

    @FXML
    public Text time6;

    @FXML
    public Text time7;

    @FXML
    public Text time8;

    @FXML
    public Text time9;

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();

    }
    public void initialize () {
        imageView.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name.getText());
        });
        imageView1.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name1.getText());
        });
        imageView2.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name2.getText());
        });
        imageView3.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name3.getText());
        });
        imageView4.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name4.getText());
        });
        imageView5.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name5.getText());
        });
        imageView6.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name6.getText());
        });
        imageView7.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name7.getText());
        });
        imageView8.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name8.getText());
        });
        imageView9.setOnMouseClicked(mouseEvent -> {
            prosesVideo(name9.getText());
        });

    }

    void prosesVideo(String name) {
        Database database=Database.getDatabase();
        Content targetContent=content1;
        for (Content content :database.getDatabase().getContents()){
            if (content.getTitle().equals(name)){
                targetContent=content;
            }
        }
        UserController.getUserController().playContent(targetContent.getID());
        PlayVideo3Controller.getInstance().getUrlContent(targetContent.getLink());
        PlayVideo3Controller.getInstance().contentId.setText(String.valueOf(targetContent.getID()));
        PlayVideo3Controller.getInstance().contentName.setText(targetContent.getTitle());
        PlayVideo3Controller.getInstance().numberOfLikes.setText(String.valueOf(targetContent.getLikes()));
        PlayVideo3Controller.getInstance().numberOfViews.setText(String.valueOf(targetContent.getViews()));
        addComment(targetContent);
        MainPage.stage.setScene(MainPage.playVideo3);
        MainPage.stage.show();
    }
    void addComment(Content content) {
        for (Comment c : content.getComments()) {
            PlayVideoController.getInstance().comment.getItems().add("user:" + c.getCommenter().getUserName() + " | " + "comment:" + c.getComment());
        }
    }

}
