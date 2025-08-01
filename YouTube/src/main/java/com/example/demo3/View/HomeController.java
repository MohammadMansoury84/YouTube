package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Comment;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.demo3.MainPage.channel;

public class HomeController {
    Channel channel1=new Channel(null,null,null);
    Content content1=new Content(null,null,null,null,null);
    @FXML
    private Rectangle timeArea;
    @FXML
    private ImageView channelimage;

    @FXML
    private ImageView contentImage;

    @FXML
    private Text chanelName;

    @FXML
    private Text name;

    @FXML
    private TextField searchbar;

    @FXML
    private Text time;

    @FXML
    void goToChannel(ActionEvent event) {
        MainPage.stage.setScene(channel);
        MainPage.stage.show();

    }

    @FXML
    void goToHome(ActionEvent event) {
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();

    }

    @FXML
    void goToLibrary(ActionEvent event) {
        MainPage.stage.setScene(MainPage.libraryScene);
        MainPage.stage.show();
    }

    @FXML
    void goToProfile(ActionEvent event) {
        MainPage.stage.setScene(MainPage.profileScene);
        MainPage.stage.show();
    }

    @FXML
    void goToSubscription(ActionEvent event) {
        MainPage.stage.setScene(MainPage.subscription);
        MainPage.stage.show();
        SubscriptionController.getInstance().initialize();
    }

    @FXML
    void logOut(ActionEvent event) {
        UserController.getUserController().logout();
        MainPage.stage.setScene(MainPage.loginScene);
        MainPage.stage.show();
        LibraryController.getInstance().playList.getItems().clear();
        InformationChannelController.getInstance().playListList.getItems().clear();
        AddContentsToChannelController.getInstance().contentList.getItems().clear();
        AddContentsToChannelController.getInstance().num=0;
        SubscriptionController.getInstance().Subscriptions.getItems().clear();
    }

    @FXML
    void search(ActionEvent event) {
        Database database=Database.getDatabase();
        for (Channel channel : database.getDatabase().getChannels()) {
            if (channel.getName().toLowerCase().contains(searchbar.getText())) {
                channelimage.setImage(new Image(channel.getCover()));
                chanelName.setText("name :"+channel.getName());
                timeArea.setVisible(true);
                channelimage.setOnMouseClicked(mouseEvent ->{
                    for (Content content : channel.getContents()){
                        ShowChannelInfoController.getInstance().contents.getItems().add(content.getTitle());
                    }
                    ShowChannelInfoController.getInstance().getChannel(channel);
                    ShowChannelInfoController.getInstance().subscribers.setText(String.valueOf(channel.getSubscribers().size()));
                    ShowChannelInfoController.getInstance().channelName.setText(channel.getName());
                    ShowChannelInfoController.getInstance().id.setText(String.valueOf(channel.getId()));
                    ShowChannelInfoController.getInstance().description.setText(channel.getDescription());
                    ShowChannelInfoController.getInstance().cover.setImage(new Image(channel.getCover()));
                    MainPage.stage.setScene(MainPage.showChannelInfo);
                    MainPage.stage.show();
                });


            }
        }

        for (Content content : database.getDatabase().getContents()) {
            if (content.getTitle().toLowerCase().contains(searchbar.getText())) {
                contentImage.setImage(new Image(content.getCover()));
                name.setText("name :"+content.getTitle());
                time.setText(content.getDuration());
                contentImage.setOnMouseClicked(mouseEvent -> {
                    PlayVideo2Controller.getInstance().getUrlContent(content.getLink());
                    UserController.getUserController().playContent(content.getID());
                    PlayVideo2Controller.getInstance().contentId.setText(String.valueOf(content.getID()));
                    PlayVideo2Controller.getInstance().numberOfViews.setText(String.valueOf(content.getViews()));
                    PlayVideo2Controller.getInstance().numberOfLikes.setText(String.valueOf(content.getLikes()));
                    PlayVideo2Controller.getInstance().contentName.setText(String.valueOf(content.getTitle()));
                    addComment(content);

                    MainPage.stage.setScene(MainPage.playVideo2);
                   MainPage.stage.show();

                });

            }
        }
    }
    void addComment(Content content){
        for (Comment c : content.getComments()){
            PlayVideo2Controller.getInstance().comment.getItems().add(c.getComment());
        }
    }
    @FXML
    void suggested(ActionEvent event) {
        SuggestedController.getInstance().imageView.setImage(new Image(UserController.getUserController().getSuggestedContents().get(0).getCover()));
        SuggestedController.getInstance().imageView1.setImage(new Image(UserController.getUserController().getSuggestedContents().get(1).getCover()));
        SuggestedController.getInstance().imageView2.setImage(new Image(UserController.getUserController().getSuggestedContents().get(2).getCover()));
        SuggestedController.getInstance().imageView3.setImage(new Image(UserController.getUserController().getSuggestedContents().get(3).getCover()));
        SuggestedController.getInstance().imageView4.setImage(new Image(UserController.getUserController().getSuggestedContents().get(4).getCover()));
        SuggestedController.getInstance().imageView5.setImage(new Image(UserController.getUserController().getSuggestedContents().get(5).getCover()));
        SuggestedController.getInstance().imageView6.setImage(new Image(UserController.getUserController().getSuggestedContents().get(6).getCover()));
        SuggestedController.getInstance().imageView7.setImage(new Image(UserController.getUserController().getSuggestedContents().get(7).getCover()));
        SuggestedController.getInstance().imageView8.setImage(new Image(UserController.getUserController().getSuggestedContents().get(8).getCover()));
        SuggestedController.getInstance().imageView9.setImage(new Image(UserController.getUserController().getSuggestedContents().get(9).getCover()));
        SuggestedController.getInstance().name.setText(UserController.getUserController().getSuggestedContents().get(0).getTitle());
        SuggestedController.getInstance().name1.setText(UserController.getUserController().getSuggestedContents().get(1).getTitle());
        SuggestedController.getInstance().name2.setText(UserController.getUserController().getSuggestedContents().get(2).getTitle());
        SuggestedController.getInstance().name3.setText(UserController.getUserController().getSuggestedContents().get(3).getTitle());
        SuggestedController.getInstance().name4.setText(UserController.getUserController().getSuggestedContents().get(4).getTitle());
        SuggestedController.getInstance().name5.setText(UserController.getUserController().getSuggestedContents().get(5).getTitle());
        SuggestedController.getInstance().name6.setText(UserController.getUserController().getSuggestedContents().get(6).getTitle());
        SuggestedController.getInstance().name7.setText(UserController.getUserController().getSuggestedContents().get(7).getTitle());
        SuggestedController.getInstance().name8.setText(UserController.getUserController().getSuggestedContents().get(8).getTitle());
        SuggestedController.getInstance().name9.setText(UserController.getUserController().getSuggestedContents().get(9).getTitle());
        SuggestedController.getInstance().time.setText(UserController.getUserController().getSuggestedContents().get(0).getDuration());
        SuggestedController.getInstance().time1.setText(UserController.getUserController().getSuggestedContents().get(1).getDuration());
        SuggestedController.getInstance().time2.setText(UserController.getUserController().getSuggestedContents().get(2).getDuration());
        SuggestedController.getInstance().time3.setText(UserController.getUserController().getSuggestedContents().get(3).getDuration());
        SuggestedController.getInstance().time4.setText(UserController.getUserController().getSuggestedContents().get(4).getDuration());
        SuggestedController.getInstance().time5.setText(UserController.getUserController().getSuggestedContents().get(5).getDuration());
        SuggestedController.getInstance().time6.setText(UserController.getUserController().getSuggestedContents().get(6).getDuration());
        SuggestedController.getInstance().time7.setText(UserController.getUserController().getSuggestedContents().get(7).getDuration());
        SuggestedController.getInstance().time8.setText(UserController.getUserController().getSuggestedContents().get(8).getDuration());
        SuggestedController.getInstance().time9.setText(UserController.getUserController().getSuggestedContents().get(9).getDuration());
        MainPage.stage.setScene(MainPage.suggested);
        MainPage.stage.show();
    }

}
