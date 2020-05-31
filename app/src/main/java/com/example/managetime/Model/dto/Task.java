package com.example.managetime.Model.dto;

import java.util.List;

public class Task {
    private int id;
    private String title;
    private String taskDescription;
    private String duration;
    private String startTime;
    private String endTime;
    private boolean isDone;
    private List<Task> subtasks;

    public Task(int id, String title, String taskDescription, String duration, String startTime, String endTime, boolean isDone, String priority, List<Task> subtasks) {
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
        this.priority = priority;
        this.subtasks = subtasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }

    private String priority;
}
