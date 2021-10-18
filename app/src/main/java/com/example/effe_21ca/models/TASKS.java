package com.example.effe_21ca.models;


public class TASKS {


    String title,link,taskId;
    int points;
    Long timestamp;



    public TASKS(String title, String link, String taskId, int points) {
        this.title = title;
        this.link = link;
        this.taskId = taskId;
        this.points = points;
    }


    public TASKS(String title, String link, String taskId, int points, Long timestamp) {

         this.title = title;
        this.link = link;
        this.taskId = taskId;
        this.points = points;
        this.timestamp = timestamp;
    }

    public  Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

//    public void settaskId(String taskId) {
//        taskId = taskId;
//    }

    public TASKS() {
    }

    public String getTaskId() {
        return taskId;
    }

    public TASKS(String title, String link, int points) {
        this.title = title;
        this.link = link;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {

        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}