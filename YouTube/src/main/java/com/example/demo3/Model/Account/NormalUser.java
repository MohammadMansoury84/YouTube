package com.example.demo3.Model.Account;


public class NormalUser extends User {
    private static final int maxPlayList=3;
    private static final int maxContentsPerPl=10;

    public NormalUser(String userName, String password, String name, String lastname, String email, String phoneNumber,String profileCover ){
        super(userName, password,name,lastname, email, phoneNumber, profileCover);
    }
    public int getMaxContentsPerPl() {
        return maxContentsPerPl;
    }
    public int getMaxPlayList() {
        return maxPlayList;
    }

}
