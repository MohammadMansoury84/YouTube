package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class CategoryController {
    ArrayList<String> category=new ArrayList<>();
    @FXML
    private RadioButton english;

    @FXML
    private RadioButton game;

    @FXML
    private RadioButton history;

    @FXML
    private RadioButton live;

    @FXML
    private RadioButton music;

    @FXML
    private RadioButton news;

    @FXML
    private RadioButton podcast;

    @FXML
    private RadioButton society;

    @FXML
    private RadioButton sport;

    @FXML
    void english(ActionEvent event) {
        if (english.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                english.setSelected(false);
            }
            else if (!category.contains("ENGLISH,")) {
                category.add("ENGLISH,");
            }
        }
        else {
            category.remove("ENGLISH,");
        }
    }

    @FXML
    void game(ActionEvent event) {
        if (game.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                game.setSelected(false);
            }
            else if (!category.contains("GAME,")) {
                category.add("GAME,");
            }
        }
        else {
            category.remove("GAME,");
        }
    }

    @FXML
    void history(ActionEvent event) {
        if (history.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                history.setSelected(false);
            }
            else if (!category.contains("HISTORY,")) {
                category.add("HISTORY");
            }
        }
        else {
            category.remove("HISTORY,");
        }
    }

    @FXML
    void live(ActionEvent event) {
        if (live.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                live.setSelected(false);
            }
            else if (!category.contains("LIVE,")) {
                category.add("LIVE,");
            }
        }
        else {
            category.remove("LIVE,");
        }
    }

    @FXML
    void music(ActionEvent event) {
        if (music.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                music.setSelected(false);
            }
            else if (!category.contains("MUSIC,")) {
                category.add("MUSIC,");
            }
        }
        else {
            category.remove("MUSIC,");
        }
    }

    @FXML
    void news(ActionEvent event) {
        if (news.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                news.setSelected(false);
            }
            else if (!category.contains("NEWS,")) {
                category.add("NEWS,");
            }
        }
        else {
            category.remove("NEWS,");
        }
    }

    @FXML
    void podcast(ActionEvent event) {
        if (podcast.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                podcast.setSelected(false);
            }
            else if (!category.contains("PODCAST,")) {
                category.add("PODCAST,");
            }
        }
        else {
            category.remove("PODCAST,");
        }
    }

    @FXML
    void society(ActionEvent event) {
        if (society.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                society.setSelected(false);
            }
            else if (!category.contains("SOCIETY,")) {
                category.add("SOCIETY,");
            }
        }
        else {
            category.remove("SOCIETY,");
        }
    }

    @FXML
    void sport(ActionEvent event) {
        if (sport.isSelected()) {
            if (category.size() >= 4) {
                showAlert("The number of selections exceeds the allowed limit (only four selections)");
                sport.setSelected(false);
            }
            else if (!category.contains("SPORT,")) {
                category.add("SPORT,");
            }
        }
        else {
            category.remove("SPORT,");
        }
    }

    @FXML
    void addToCategorys(ActionEvent event) {
        String str=category.get(0)+category.get(1)+category.get(2)+category.get(3);
        if (UserController.getUserController().selectFavouriteCategories(str).equals("Successfully selected")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successfully selected");
            alert.showAndWait();
        }

        // رفتن به صفحه home
        society.setSelected(false);
        sport.setSelected(false);
        podcast.setSelected(false);
        live.setSelected(false);
        news.setSelected(false);
        music.setSelected(false);
        game.setSelected(false);
        history.setSelected(false);
        english.setSelected(false);
        MainPage.stage.setScene(MainPage.home);
        MainPage.stage.show();


    }


    void showAlert(String massage){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }

}
