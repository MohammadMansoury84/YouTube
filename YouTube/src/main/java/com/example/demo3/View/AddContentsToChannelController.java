package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Comment;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class AddContentsToChannelController {
    private static AddContentsToChannelController instance;

    public AddContentsToChannelController(){
        instance = this;
    }
    public static AddContentsToChannelController getInstance() {
        return instance;
    }

    public int num=0;
    @FXML
    private Label Description;

    @FXML
    private Label file;

    @FXML
    private Label Quality;

    @FXML
    private RadioButton Quality1080;

    @FXML
    private RadioButton Quality360;

    @FXML
    private RadioButton Quality480;

    @FXML
    private RadioButton Quality720;

    @FXML
    private RadioButton Regular;

    @FXML
    private RadioButton Special;

    @FXML
    private TextField Title;

    @FXML
    private Label catergory;

    @FXML
    private ToggleGroup cc;

    @FXML
    private Button choose;

    @FXML
    private Label content;

    @FXML
    public ListView<String> contentList;

    @FXML
    private Label cover;

    @FXML
    private TextArea description;

    @FXML
    private TextField duration;

    @FXML
    private RadioButton english;

    @FXML
    private ToggleGroup ff;

    @FXML
    private Label format;

    @FXML
    private RadioButton formatMKV;

    @FXML
    private RadioButton formatMOV;

    @FXML
    private RadioButton formatMP4;

    @FXML
    private RadioButton formatWMV;

    @FXML
    private RadioButton game;

    @FXML
    private ToggleGroup hh;

    @FXML
    private RadioButton history;

    @FXML
    private RadioButton live;

    @FXML
    private ToggleGroup mm;

    @FXML
    private RadioButton music;

    @FXML
    private Label name;

    @FXML
    private RadioButton news;
    private int id=1;

    @FXML
    private RadioButton podcast;

    @FXML
    private TextField podcaster;

    @FXML
    private ToggleGroup pp;

    @FXML
    private Separator s1;

    @FXML
    private Separator s2;

    @FXML
    private Button select;

    @FXML
    private RadioButton society;

    @FXML
    private RadioButton sport;

    @FXML
    private RadioButton podcastType;

    @FXML
    private Label time;

    @FXML
    private RadioButton NormalVideoType;

    @FXML
    private Label title;
    private String formatType;
    private String categoryType;
    private String specialOrRegular;
    private String qualityType;
    private String imageUrl;
    private String podcastOrVideoUrl;
    String contentId;


    @FXML
    void Quality1080(ActionEvent event) {
        if (Quality1080.isSelected()){
            qualityType="1080";
        }

    }

    @FXML
    void Quality360(ActionEvent event) {
        if (Quality360.isSelected()){
            qualityType="360";
        }
    }

    @FXML
    void Quality480(ActionEvent event) {
        if (Quality480.isSelected()){
            qualityType="480";
        }
    }

    @FXML
    void Quality720(ActionEvent event) {
        if (Quality720.isSelected()){
            qualityType="720";
        }
    }
    private int makeId(){
        return id++;
    }


    @FXML
    void add(ActionEvent event) {
        String result;
        if (podcastType.isSelected()){
            result= UserController.getUserController().PublishPodcast(specialOrRegular,Title.getText(),description.getText(),duration.getText(),categoryType, podcastOrVideoUrl,imageUrl,podcaster.getText());
        }
        else {
            result=UserController.getUserController().PublishNormalVideo(specialOrRegular,Title.getText(),description.getText(),duration.getText(),categoryType, podcastOrVideoUrl,imageUrl,null,qualityType,formatType);
        }
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if (result.equals("Error: User does not have a channel or playlists.")){
            alert.setContentText("Error: User does not have a channel or playlists.");
        }
        else {
            alert.setContentText("published successfully");
        }
        alert.showAndWait();
        contentList.getItems().add(String.valueOf(makeId())+"-"+Title.getText());
        num=0;

    }

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.informationChannel);
        MainPage.stage.show();
        Regular.setVisible(false);
        Special.setVisible(false);
        Title.setVisible(false);
        title.setVisible(false);
        description.setVisible(false);
        Description.setVisible(false);
        duration.setVisible(false);
        time.setVisible(false);
        news.setVisible(false);
        game.setVisible(false);
        podcast.setVisible(false);
        music.setVisible(false);
        live.setVisible(false);
        society.setVisible(false);
        history.setVisible(false);
        english.setVisible(false);
        sport.setVisible(false);
        catergory.setVisible(false);
        select.setVisible(false);
        file.setVisible(false);
        cover.setVisible(false);
        choose.setVisible(false);
        s2.setVisible(false);
        content.setVisible(false);
        formatMKV.setVisible(false);
        formatMOV.setVisible(false);
        formatWMV.setVisible(false);
        formatMP4.setVisible(false);
        format.setVisible(false);
        Quality.setVisible(false);
        Quality360.setVisible(false);
        Quality480.setVisible(false);
        Quality720.setVisible(false);
        Quality1080.setVisible(false);
        Quality.setVisible(false);
        s1.setVisible(false);
        podcaster.setVisible(false);
        name.setVisible(false);
        Title.clear();
        description.clear();
        duration.clear();
        podcaster.clear();

    }

    @FXML
    void choose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage)select.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        imageUrl =selectedFile.getAbsolutePath();
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully selected");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void english(ActionEvent event) {
        if(english.isSelected()){
            categoryType="ENGLISH";
        }

    }
    @FXML
    void formatMKV(ActionEvent event) {
        if (formatMKV.isSelected()){
            formatType="MKV";
        }
    }

    @FXML
    void formatMOV(ActionEvent event) {
        if (formatMOV.isSelected()){
            formatType="MOV";
        }
    }

    @FXML
    void formatMP4(ActionEvent event) {
        if (formatMP4.isSelected()){
            formatType="MP4";
        }
    }

    @FXML
    void formatWMV(ActionEvent event) {
        if (formatWMV.isSelected()){
            formatType="WMV";
        }
    }

    @FXML
    void game(ActionEvent event) {
        if(game.isSelected()){
            categoryType="GAME";
        }
    }

    @FXML
    void history(ActionEvent event) {
        if(history.isSelected()){
            categoryType="HISTORY";
        }
    }

    @FXML
    void live(ActionEvent event) {
        if(live.isSelected()){
            categoryType="LIVE";
        }
    }

    @FXML
    void music(ActionEvent event) {
        if(music.isSelected()){
            categoryType="MUSIC";
        }
    }

    @FXML
    void news(ActionEvent event) {
        if(news.isSelected()){
            categoryType="NEWS";
        }
    }

    @FXML
    void podcast(ActionEvent event) {
        if(podcast.isSelected()){
            categoryType="PODCAST";
        }
    }

    @FXML
    void regular(ActionEvent event) {
        if (Regular.isSelected()){
            specialOrRegular="N";
        }

    }

    @FXML
    void select(ActionEvent event) {
        if (NormalVideoType.isSelected()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a Video");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.wvm", "*.mkv", "*.mov"));
            Stage stage = (Stage) select.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                podcastOrVideoUrl = selectedFile.getAbsolutePath();
            }
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully selected");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Audio File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.flac"));
            Stage stage = (Stage) select.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                podcastOrVideoUrl = selectedFile.getAbsolutePath();
            }
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully selected");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    void society(ActionEvent event) {
        if(society.isSelected()){
            categoryType="SOCIETY";
        }

    }

    @FXML
    void special(ActionEvent event) {
        if (Special.isSelected()){
            specialOrRegular="Y";
        }
    }

    @FXML
    void sport(ActionEvent event) {
        if(sport.isSelected()){
            categoryType="SPORT";
        }
    }

    @FXML
    void NormalVideo(ActionEvent event) {
        Regular.setVisible(true);
        Special.setVisible(true);
        Title.setVisible(true);
        title.setVisible(true);
        description.setVisible(true);
        Description.setVisible(true);
        duration.setVisible(true);
        time.setVisible(true);
        news.setVisible(true);
        game.setVisible(true);
        podcast.setVisible(true);
        music.setVisible(true);
        live.setVisible(true);
        society.setVisible(true);
        history.setVisible(true);
        english.setVisible(true);
        sport.setVisible(true);
        catergory.setVisible(true);
        select.setVisible(true);
        file.setVisible(true);
        cover.setVisible(true);
        contentList.setVisible(true);
        choose.setVisible(true);
        s2.setVisible(true);
        content.setVisible(true);
        formatMKV.setVisible(true);
        formatMOV.setVisible(true);
        formatWMV.setVisible(true);
        formatMP4.setVisible(true);
        format.setVisible(true);
        Quality.setVisible(true);
        Quality360.setVisible(true);
        Quality480.setVisible(true);
        Quality720.setVisible(true);
        Quality1080.setVisible(true);
        Quality.setVisible(true);
        s1.setVisible(true);
        podcaster.setVisible(false);
        name.setVisible(false);




    }
    @FXML
    void podcastType(ActionEvent event) {
        Regular.setVisible(true);
        Special.setVisible(true);
        Title.setVisible(true);
        title.setVisible(true);
        description.setVisible(true);
        Description.setVisible(true);
        duration.setVisible(true);
        time.setVisible(true);
        news.setVisible(true);
        game.setVisible(true);
        podcast.setVisible(true);
        music.setVisible(true);
        live.setVisible(true);
        society.setVisible(true);
        history.setVisible(true);
        english.setVisible(true);
        sport.setVisible(true);
        catergory.setVisible(true);
        select.setVisible(true);
        file.setVisible(true);
        cover.setVisible(true);
        contentList.setVisible(true);
        choose.setVisible(true);
        s2.setVisible(true);
        content.setVisible(true);
        s1.setVisible(true);
        name.setVisible(true);
        podcaster.setVisible(true);
        formatMKV.setVisible(false);
        formatMOV.setVisible(false);
        formatWMV.setVisible(false);
        formatMP4.setVisible(false);
        format.setVisible(false);
        Quality.setVisible(false);
        Quality360.setVisible(false);
        Quality480.setVisible(false);
        Quality720.setVisible(false);
        Quality1080.setVisible(false);
        Quality.setVisible(false);


    }
    public void initialize (){

        contentList.setOnMouseClicked(mouseEvent -> {
            String item=contentList.getSelectionModel().getSelectedItem();
            processPlayList(item);
        });
    }
    void processPlayList(String item) {
        int index = item.indexOf("-");
        contentId = item.substring(0, index);
        Content targetContent = null;
        Database db = Database.getDatabase().getDatabase();

        for (Content content : db.getDatabase().getContents()) {
            if (content.getID() == Integer.parseInt(contentId)) {
                targetContent = content;
                break;
            }
        }

        if (targetContent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Content with ID not found.");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        PlayVideoController.getInstance().getUrlContent(targetContent.getLink());
        if (num<1) {
            UserController.getUserController().playContent(targetContent.getID());
        }
        PlayVideoController.getInstance().contentId.setText(String.valueOf(targetContent.getID()));
        PlayVideoController.getInstance().numberOfViews.setText(String.valueOf(targetContent.getViews()));
        PlayVideoController.getInstance().numberOfLikes.setText(String.valueOf(targetContent.getLikes()));
        PlayVideoController.getInstance().contentName.setText(String.valueOf(targetContent.getTitle()));
        addComment(targetContent);
        MainPage.stage.setScene(MainPage.playVideo);
        MainPage.stage.show();
        num++;


    }
    void addComment(Content content){
       for (Comment c : content.getComments()){
           PlayVideoController.getInstance().comment.getItems().add("user:"+c.getCommenter().getUserName()+" | "+"comment:"+c.getComment());
       }
    }







}
