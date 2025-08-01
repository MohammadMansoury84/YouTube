package com.example.demo3.Model.Account;

public enum PremiumPack {
    BRONZE(30,5),SILVER(60,9),GOLD(180,14);
    int days;
    int price;
    PremiumPack(int days,int price){
        this.days=days;
        this.price=price;
    }
    public int getDays() {
        return days;
    }
    public int getPrice() {
        return price;
    }
}
