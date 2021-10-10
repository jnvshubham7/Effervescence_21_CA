package com.example.effe_21ca.models;

public class Users {
    String userName,mail,passwoord,userId;

    int score;
    boolean isCurrentUser;

//    public Users(String userName, int score, boolean isCurrentUser) {
//
//
//        this.userName = userName;
//        this.score = score;
//        this.isCurrentUser = isCurrentUser;
//    }


    public Users(String userName, String mail) {
        this.userName = userName;
        this.mail = mail;
    }

    public Users(String userName, String mail, String passwoord) {
        this.userName = userName;
        this.mail = mail;
        this.passwoord = passwoord;
    }

    public Users(String userName, String mail, String passwoord, String userId) {
        this.userName = userName;
        this.mail = mail;
        this.passwoord = passwoord;
        this.userId = userId;
 
    }

    public Users(String userName, String mail, String passwoord, int score) {
        this.userName = userName;
        this.mail = mail;
        this.passwoord = passwoord;
        this.score = score;
 
       // this.points = points;
 
    }

    public Users(){

    }

    public void getUserId(String key) {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUid() {
        return userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswoord() {
        return passwoord;
    }

    public void setPasswoord(String passwoord) {
        this.passwoord = passwoord;
    }

    public String getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        isCurrentUser = currentUser;
    }
}
