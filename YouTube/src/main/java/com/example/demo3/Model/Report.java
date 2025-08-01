package com.example.demo3.Model;

import com.example.demo3.Model.Account.User;

public class Report {

    private User reporter;
    private int contentId;
    private String reportComment;
    private int id;

        public Report(int contentId, String reportComment){
            this.contentId=contentId;
            this.reportComment=reportComment;
        }

        public int getContentId() {
            return contentId;
        }
        public String getReportComment() {
            return reportComment;
        }
        public int getId() {
            return id;
        }
        public User getReporter() {
            return reporter;
        }
        public void setReporter(User reporter) {
            this.reporter = reporter;
        }
        public void setReportComment(String reportComment) {
            this.reportComment = reportComment;
        }
        public void setContentId(int contentId) {
            this.contentId = contentId;
        }
        public void setId(int id) {
            this.id = id;
        }
    }


