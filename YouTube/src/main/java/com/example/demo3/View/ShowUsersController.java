package com.example.demo3.View;

import com.example.demo3.MainPage;
import com.example.demo3.Model.Account.NormalUser;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Channel;
import com.example.demo3.Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ShowUsersController {
    private static ShowUsersController instance;

    public ShowUsersController(){
        instance = this;
    }
    public static ShowUsersController getInstance() {
        return instance;
    }
    User user=new NormalUser(null,null,null,null,null,null,null);


    @FXML
    private ListView<String> usersList;

    public void initialize () {
        Database db = Database.getDatabase().getDatabase();
        for (User user : db.getDatabase().getUsers()){
            usersList.getItems().add(user.getUserName());
        }

        usersList.setOnMouseClicked(mouseEvent -> {
            String item=usersList.getSelectionModel().getSelectedItem();
            processUsersList(item);
        });


    }
   void processUsersList(String item){
       User targetUser = user;
       Database db = Database.getDatabase().getDatabase();

       for (User user : db.getDatabase().getUsers()) {
           if (user.getUserName().equals(item)) {
               targetUser = user;
               break;
           }
       }
       if (targetUser == null) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("User not found.");
           alert.setHeaderText(null);
       }

       UserInfoController.getInstance().name.setText(targetUser.getName());
       UserInfoController.getInstance().userName.setText(targetUser.getUserName());
       UserInfoController.getInstance().email.setText(targetUser.getEmail());
       UserInfoController.getInstance().id.setText(String.valueOf(targetUser.getId()));
       UserInfoController.getInstance().phoneNumber.setText(targetUser.getPhoneNumber());
       UserInfoController.getInstance().profile.setImage(new Image(targetUser.getProfileCover()));
       UserInfoController.getInstance().password.setText(targetUser.getPassword());
       MainPage.stage.setScene(MainPage.userInfo);
       MainPage.stage.show();

   }



    @FXML
    void back(ActionEvent event) {
        MainPage.stage.setScene(MainPage.admin);
        MainPage.stage.show();
        usersList.getItems().clear();

    }

}
