package com.example.effe_21ca.models;

public class Users {
    String userName,mail,passwoord,userId;

    public Users( String userName, String mail, String passwoord, String userId) {

        this.userName = userName;
        this.mail = mail;
        this.passwoord = passwoord;
        this.userId = userId;
        this.points = points;
    }

    public Users(){

    }
    public Users( String userName, String mail, String passwoord) {

        this.userName = userName;
        this.mail = mail;
        this.passwoord = passwoord;
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

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
