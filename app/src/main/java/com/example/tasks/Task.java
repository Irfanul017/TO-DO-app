package com.example.tasks;

import com.google.firebase.Timestamp;

public class Task {

    private String task;
    private String details;
    private String imageURL;
    private String userId;
    private Timestamp timeAdded;
    private String userName;
    private String status;

    public Task() {
    }

    public Task(String task, String details, String imageURL, String userId, Timestamp timeAdded, String userName, String status) {
        this.task = task;
        this.details = details;
        this.imageURL = imageURL;
        this.userId = userId;
        this.timeAdded = timeAdded;
        this.userName = userName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
