package com.example.demo3.Model;

import com.example.demo3.Model.Content.Content;

import java.util.ArrayList;

public class Playlist {


        private int id;
        private String name;
        private ArrayList<Content> contents;
        private ArrayList<Content>watchLater;
        private ArrayList<Content> liked;

        public Playlist(String name) {
            if (this.contents == null) {
                this.contents = new ArrayList<>();
            }
            this.name = name;
            if (this.watchLater == null) {
                this.watchLater = new ArrayList<>();
            }
            if (this.liked == null) {
                this.liked = new ArrayList<>();
            }

        }


        public ArrayList<Content> getContents() {
            return contents;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public void setContents(ArrayList<Content> contents) {
            this.contents = contents;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Content> getLiked() {
            return liked;
        }

        public ArrayList<Content> getWatchLater() {
            return watchLater;
        }

        public void addContent(Content content) {
            contents.add(content);
        }

        public void removeContent(Content content) {
            contents.remove(content);
        }





}
