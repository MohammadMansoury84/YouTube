package com.example.demo3.Model.Account;


import java.util.Date;

public class PremiumUser extends User {
    private Date PremiumEndData;

    public PremiumUser(String userName, String password, String name, String lastname, String email, String phoneNumber, String profileCover, float credit){
        super(userName, password,name,lastname, email, phoneNumber, profileCover);
    }
    public Date getPremiumEndData() {
        return PremiumEndData;
    }
    public void setPremiumEndData(Date premiumEndData) {
        PremiumEndData = premiumEndData;
    }
}
