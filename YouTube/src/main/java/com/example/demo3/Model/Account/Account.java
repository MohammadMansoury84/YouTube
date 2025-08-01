package com.example.demo3.Model.Account;

public abstract class Account {

    private String userName;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileCover;
    private int id;

    public Account(String userName, String password,String name,String lastname, String email, String phoneNumber, String profileCover){
        this.userName=userName;
        this.password=password;
        this.name=name;
        this.lastname=lastname;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.profileCover=profileCover;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getProfileCover() {
        return profileCover;
    }
    public String getUserName() {
        return userName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
