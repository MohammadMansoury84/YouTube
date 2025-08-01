package com.example.demo3.View;

import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

public class ContentInfoController {
    private static ContentInfoController instance;

    public ContentInfoController(){
        instance = this;
    }
    public static ContentInfoController getInstance() {
        return instance;
    }

    @FXML
    public ListView<String> comentList;
    @FXML
    private Slider mediaSlider;

    @FXML
    public Text likes;

    @FXML
    public MediaView mediaView;
    public Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    public Text name;

    @FXML
    public Text views;
    public String initialize (String url) {
        try {

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
        return null;
    }

    @FXML
    void back(ActionEvent event) {
        comentList.getItems().clear();
        MainPage.stage.setScene(MainPage.showContents);
        MainPage.stage.show();
    }

    @FXML
    void pauseVideo(ActionEvent event) {
        mediaPlayer.pause();

    }

    @FXML
    void playVideo(ActionEvent event) {
        mediaPlayer.play();
    }

}
