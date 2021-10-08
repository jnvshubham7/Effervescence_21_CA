package com.example.effe_21ca.models;

public class TASKS {

    String title,link,point;

    public TASKS(String title, String link, String point) {
        this.title = title;
        this.link = link;
        this.point = point;

    }

    @Override
    public String toString() {
        return "TASKS{" +
                ", points='" + point + '\'' +

                ", link='" + link + '\'' +
                "title='" + title + '\'' +


                '}';
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


    public String getPoint() {
        return point;
    }


}
