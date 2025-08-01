package com.example.demo3.Model.Account;


import com.example.demo3.Controller.UserController;

public class Admin extends Account {
    private static Admin admin;

    public Admin(String userName, String password, String name, String lastname, String email, String phoneNumber, String profileCover) {
        super(userName,password,name,lastname,email,phoneNumber,profileCover);
    }
    public static Admin getAdmin() {
        if (admin == null) {
            admin = new Admin("Admin","123","adm","admini","admin@gmail.com","09132456789","cover");
            UserController.getUserController().database.setAdmin(admin);
        }
        return admin;
    }
}
