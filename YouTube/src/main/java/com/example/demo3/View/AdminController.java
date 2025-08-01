package com.example.demo3.View;

import com.example.demo3.MainPage;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Content.Content;
import com.example.demo3.Model.Database;
import com.example.demo3.Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class AdminController {

    @FXML
    void charts(ActionEvent event) {
        MainPage.stage.setScene(MainPage.charts);
        MainPage.stage.show();

        BarChart<String, Number> barChart = ChartsController.getInstance().content;


        barChart.setTitle("Popular content based on likes");
        barChart.setLegendVisible(true);


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Number of likes");


        ArrayList<Content> popularContents = com.example.demo3.Controller.AdminController.getAdminController().getPopularContents();


        for (Content content : popularContents) {
            series.getData().add(new XYChart.Data<>(content.getTitle(), content.getLikes()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);


        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {

                node.setStyle("-fx-bar-fill: #3498db;");
            }
        }

        barChart.getXAxis().setLabel("Content title");
        barChart.getYAxis().setLabel("Number of likes");

        BarChart<String, Number> barChart2 = ChartsController.getInstance().channel;


        barChart2.setTitle("Popular content based on subscriptions");
        barChart2.setLegendVisible(true);


        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("number of subscribers");


        ArrayList<Channel> popularChannel = com.example.demo3.Controller.AdminController.getAdminController().getPopularChannels();


        for (Content content : popularContents) {
            series.getData().add(new XYChart.Data<>(content.getTitle(), content.getLikes()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);


        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {

                node.setStyle("-fx-bar-fill: #ff0000;");
            }
        }

        barChart.getXAxis().setLabel("Content title");
        barChart.getYAxis().setLabel("number of subscribers");
    }


    @FXML
    void contents(ActionEvent event) {
        Database database = Database.getDatabase().getDatabase();
        for (Content content:database.getDatabase().getContents()){
            ShowContentsController.getInstance().contentList.getItems().add(content.getTitle());
        }
        MainPage.stage.setScene(MainPage.showContents);
        MainPage.stage.show();
    }

    @FXML
    void logout(ActionEvent event) {
        MainPage.stage.setScene(MainPage.loginScene);
        MainPage.stage.show();
    }

    @FXML
    void reports(ActionEvent event) {
        Database database=Database.getDatabase().getDatabase();
        for (Report report:database.getDatabase().getReports()){
            ShowReportsController.getInstance().reportList.getItems().add("Report id:"+report.getId()+" | "+"Content id:"+report.getContentId()+" | "+"Reporter username:"+report.getReporter().getUserName()+" | "+"Report comment:"+report.getReportComment());
        }
        MainPage.stage.setScene(MainPage.showReports);
        MainPage.stage.show();
    }

    @FXML
    void users(ActionEvent event) {
        MainPage.stage.setScene(MainPage.showUsers);
        MainPage.stage.show();
        ShowUsersController.getInstance().initialize();
    }

}
