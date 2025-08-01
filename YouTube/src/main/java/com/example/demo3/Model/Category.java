package com.example.demo3.Model;

public enum Category {
    NEWS,GAME,PODCAST,MUSIC,LIVE,SOCIETY,HISTORY,ENGLISH,SPORT;
    public static boolean isValid(String category) {
        for (Category c : Category.values()) {
            if (c.name().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    }
