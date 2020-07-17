package com.vinu.internship;

public class Notification {
    private String Title;
    private String Description;
    private String Date;

    public Notification(String title, String description, String date) {
        Title = title;
        Description = description;
        Date = date;
    }

    public Notification() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
