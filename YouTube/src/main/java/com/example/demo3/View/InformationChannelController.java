package com.example.demo3.View;

import com.example.demo3.Controller.UserController;
import com.example.demo3.MainPage;
import com.example.demo3.Model.Channel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class InformationChannelController {

    @FXML
    private Text description;

    @FXML
    private ImageView image;

    @FXML
    private Rectangle imageBack;

    @FXML
    private Label name;

    @FXML
    private Separator s1;

    @FXML
    private Separator s2;
    
    @FXML
    public ListView<String> playListList;

    @FXML
    private Label playList;

    private static InformationChannelController instance;

    public InformationChannelController(){
        instance = this;
    }
    public static InformationChannelController getInstance() {
        return instance;
    }

    @FXML
    void see(ActionEvent event) {
        s1.setVisible(true);
        s2.setVisible(true);
        name.setVisible(true);
        imageBack.setVisible(true);
        image.setVisible(true);
        description.setVisible(true);
        playListList.setVisible(true);
        playList.setVisible(true);
        playListList.getItems().add("allContents");
        Image images=new Image(UserController.getUserController().channel.getCover());
        image.setImage(images);
        name.setText(UserController.getUserController().channel.getName());
        description.setText(UserController.getUserController().channel.getDescription());
    }
    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.channel);
        MainPage.stage.show();
        s1.setVisible(false);
        s2.setVisible(false);
        name.setVisible(false);
        imageBack.setVisible(false);
        image.setVisible(false);
        description.setVisible(false);
        playListList.setVisible(false);
        playList.setVisible(false);
    }
    public void initialize (){
        playListList.setOnMouseClicked(mouseEvent -> {
            String item=playListList.getSelectionModel().getSelectedItem();
            processPlayList();
        });
    }
    private void processPlayList(){
        MainPage.stage.setScene(MainPage.addContentsToChannel);
        MainPage.stage.show();
    }


}
