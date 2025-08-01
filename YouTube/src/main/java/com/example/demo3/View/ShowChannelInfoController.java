package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Comment;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.demo3.MainPage.channel;

public class ShowChannelInfoController {
    private static ShowChannelInfoController instance;

    public ShowChannelInfoController(){
        instance = this;
    }
    public static ShowChannelInfoController getInstance() {
        return instance;
    }
    @FXML
    public ListView<String> contents;

    @FXML
    public Text channelName;

    @FXML
    public ImageView cover;

    @FXML
    public Text description;

    @FXML
    public Text id;
    int num=0;

    @FXML
    public Text subscribers;
    private Channel targetChannel;
    Content content=new Content(null,null,null,null,null);

    public void getChannel(Channel channel){
        this.targetChannel=channel;
    }

    @FXML
    void subscribe(ActionEvent event) {
        UserController.getUserController().subChannel(Integer.parseInt(id.getText()));
        subscribers.setText(String.valueOf(targetChannel.getSubscribers().size()));
    }

    @FXML
    void unsubscribe(ActionEvent event) {
        UserController.getUserController().unsubChannel(Integer.parseInt(id.getText()));
        subscribers.setText(String.valueOf(targetChannel.getSubscribers().size()));

    }
    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.subscription);
        MainPage.stage.show();

    }
    public void initialize () {
        contents.setOnMouseClicked(mouseEvent -> {
            String item=contents.getSelectionModel().getSelectedItem();
            processSubscriptions(item);
            num=0;
        });
    }
   void processSubscriptions(String item) {
       Content targetContent = content;
       Database db = Database.getDatabase().getDatabase();
       for (Content content1 : db.getContents()) {
           if (content1.getTitle().equals(item)) {
               targetContent = content1;
               break;
           }
       }
       if (targetContent == null) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Content not found.");
           alert.setHeaderText(null);
       }
       PlayVideoController.getInstance().getUrlContent(targetContent.getLink());
       if (num < 1) {
           UserController.getUserController().playContent(targetContent.getID());
       }
       PlayVideoController.getInstance().contentId.setText(String.valueOf(targetContent.getID()));
       PlayVideoController.getInstance().numberOfViews.setText(String.valueOf(targetContent.getViews()));
       PlayVideoController.getInstance().numberOfLikes.setText(String.valueOf(targetContent.getLikes()));
       PlayVideoController.getInstance().contentName.setText(String.valueOf(targetContent.getTitle()));
       addComment(targetContent);
       Stage stage=new Stage();
       stage.setScene(MainPage.playVideo);
       stage.show();

       num++;

   }

    void addComment(Content content){
        for (Comment c : content.getComments()){
            PlayVideoController.getInstance().comment.getItems().add(c.getComment());
        }
    }


}
