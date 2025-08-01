package com.example.demo3.Model;

import com.example.demo3.Model.Account.Admin;
import com.example.demo3.Model.Account.User;
import com.example.demo3.Model.Content.Content;

import java.util.ArrayList;

public class Database {

        private static Database database;
        ArrayList<Channel> channels;
        private ArrayList<Content> contents;
        private ArrayList<Report> reports;
        private ArrayList<User> users;
        private Admin admin;

        public Database() {
            if (this.channels==null){
                this.channels = new ArrayList<>();}
            if (this.contents==null){
                this.contents = new ArrayList<>();}
            if (this.reports==null){
                this.reports =  new ArrayList<>();}
            if (this.users==null){
                this.users = new ArrayList<>();}
        }

        public static  Database getDatabase() {
            if (database == null) {
                database = new Database();
            }
            return database;
        }

        public ArrayList<Channel> getChannels() {
            return channels;
        }

        public void setChannels(ArrayList<Channel> channels) {
            this.channels = channels;
        }

        public Admin getAdmin() {
            return admin;
        }
        public ArrayList<Content> getContents() {
            return contents;
        }
        public ArrayList<Report> getReports() {
            return reports;
        }
        public ArrayList<User> getUsers() {
            return users;
        }
        public void setAdmin(Admin admin) {
            this.admin = admin;
        }
        public void setContents(ArrayList<Content> contents) {
            this.contents = contents;
        }
        public void setReports(ArrayList<Report> reports) {
            this.reports = reports;
        }
        public void setUsers(ArrayList<User> users) {
            this.users = users;
        }


}
