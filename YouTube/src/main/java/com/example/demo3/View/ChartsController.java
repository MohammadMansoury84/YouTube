package com.example.demo3.View;

import com.example.demo3.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;

public class ChartsController {
    private static ChartsController instance;

    public ChartsController(){
        instance = this;
    }
    public static ChartsController getInstance() {
        return instance;
    }

    @FXML
   public BarChart<String, Number> channel;

    @FXML
   public BarChart<String, Number> content;

    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.admin);
        MainPage.stage.show();
        content.getData().clear();
        channel.getData().clear();

    }

}
