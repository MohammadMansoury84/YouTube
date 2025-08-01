package com.example.demo3.View;

import com.example.demo3.Controller.AdminController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import com.example.demo3.Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class ShowReportsController {
    private static ShowReportsController instance;

    public ShowReportsController(){
        instance = this;
    }
    public static ShowReportsController getInstance() {
        return instance;
    }
    Content contents=new Content(null,null,null,null,null);
    Report report=new Report(0,null);

    @FXML
    private RadioButton acceptBtn;

    @FXML
    private TextField contentId;

    @FXML
    public MediaView mediaView;
    public Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private RadioButton rejectBtn;

    @FXML
    public TextField reportId;

    @FXML
    public ListView<String> reportList;

    String acceptOrReject;
    @FXML
    private ToggleGroup ff;

    @FXML
    void accept(ActionEvent event) {
        if (acceptBtn.isSelected()){
            acceptOrReject="A";
        }

    }

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.admin);
        MainPage.stage.show();
        reportList.getItems().clear();

    }
    String processVideo(String id){
        Database database =Database.getDatabase();
        Content targetContent=contents;
        for (Content content :database.getDatabase().getContents()) {
            if (content.getID() == Integer.parseInt(id)) {
                targetContent = content;
                break;
            }
        }
        if (targetContent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("content not found.");
            alert.setHeaderText(null);
        }
        try {
            String url=targetContent.getLink();
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

        return null;
    }

    @FXML
    void manage(ActionEvent event) {
        String result= AdminController.getAdminController().handleReport(acceptOrReject, Integer.parseInt(reportId.getText()));
        if (result.equals("Report with ID not found.")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Report with ID not found.");
            alert.show();
        }
        else if (result.equals("The report was approved and the content with ID was removed from the database and channels.")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The report was approved and the content with ID was removed from the database and channels.");
            alert.show();
            contentId.setText("0");
            mediaPlayer.stop();
            Database database =Database.getDatabase();
            Report targetReport=report;
            for (Report report1 :database.getDatabase().getReports()) {
                if (report1.getId() == Integer.parseInt(reportId.getText())) {
                    database.getReports().remove(report1);
                    break;
                }
            }
            if (targetReport == null) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("report not found.");
                alert2.setHeaderText(null);
            }

        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Report with ID was rejected.");
            alert.show();
        }
        reportList.getItems().clear();
        Database database=Database.getDatabase().getDatabase();
        for (Report report:database.getDatabase().getReports()){
           reportList.getItems().add("Report id:"+report.getId()+" | "+"Content id:"+report.getContentId()+" | "+"Reporter username:"+report.getReporter().getUserName()+" | "+"Report comment:"+report.getReportComment());
        }

        reportId.clear();
    }

    @FXML
    void pauseVideo(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void playVideo(ActionEvent event) {
        processVideo(contentId.getText());
        mediaPlayer.play();
    }

    @FXML
    void reject(ActionEvent event) {
        if (rejectBtn.isSelected()){
            acceptOrReject="R";
        }
    }

}
