package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class PlayVideo2Controller {
    private static PlayVideo2Controller instance;

    public PlayVideo2Controller(){
        instance = this;
    }
    public static PlayVideo2Controller getInstance() {
        return instance;
    }
    Content content=new Content(null,null,null,null,null);
    @FXML
    public MediaView mediaView;
    public Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private Slider mediaSlider;

    @FXML
    private Slider volume;
    @FXML
    public ListView<String> comment;
    @FXML
    public Text numberOfLikes;

    @FXML
    public Text numberOfViews;

    private String urlContent;

    @FXML
    public Text contentId;
    @FXML
    public Text contentName;



    private Content getContent(){
        Content targetContent =content;
        Database db = Database.getDatabase().getDatabase();

        for (Content content : db.getDatabase().getContents()) {
            if (content.getLink().equals(urlContent)) {
                targetContent = content;
                break;
            }
        }

        if (targetContent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Content with ID not found.");
            alert.setHeaderText(null);

        }
        return targetContent;
    }




    public String getUrlContent(String url) {
        try {
            urlContent=url;
            getContent();
            File file = new File(url);
            if (!file.exists()) {
                return "File not found: " + url;
            }
            String validUri = file.toURI().toString();
            Media media = new Media(validUri);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            this.mediaPlayer = mediaPlayer;
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }

        mediaPlayer.currentTimeProperty().addListener((observableTime, oldTime, newTime) -> {
            mediaSlider.setValue(newTime.toSeconds());
        });

        mediaSlider.setOnMouseReleased(event -> {
            mediaPlayer.seek(Duration.seconds(mediaSlider.getValue()));
        });

        mediaPlayer.setOnReady(() -> {
            mediaSlider.setMax(media.getDuration().toSeconds());
            mediaSlider.setValue(0);
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaSlider.setValue(0);
        });

        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener((observableVolume, oldVolume, newVolume) -> {
            mediaPlayer.setVolume(newVolume.doubleValue() / 100);
        });
        return null;
    }

    @FXML
    void pauseVideo(ActionEvent event) {
        mediaPlayer.pause();
    }
    @FXML
    void playVideo(ActionEvent event) {
        if (!UserController.getUserController().user.isPremium() &&  !getContent().getIsExclusive()) {
            mediaPlayer.play();
        }
        else {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("this Content is Exclusive and you are not premium.");
            alert.showAndWait();
        }
    }
    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();
        comment.getItems().clear();
    }

    @FXML
    void addComment(ActionEvent event) {
        Stage newStage=new Stage();
        newStage.setScene(MainPage.comment);
        newStage.show();

    }
    @FXML
    void disLike(ActionEvent event) {
        UserController.getUserController().disLikeContent(getContent().getID());
        numberOfLikes.setText(String.valueOf(getContent().getLikes()));
    }

    @FXML
    void like(ActionEvent event) {
        UserController.getUserController().likeContent(getContent().getID());
        numberOfLikes.setText(String.valueOf(getContent().getLikes()));
    }

    @FXML
    void report(ActionEvent event) {
        Stage stage=new Stage();
        stage.setScene(MainPage.report);
        stage.show();
    }

    public void initialize (){
        numberOfLikes.setText(String.valueOf(getContent().getLikes()));
    }


}
