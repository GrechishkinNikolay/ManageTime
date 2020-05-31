package com.example.managetime.Model.dao;

public class TaskDAO {
    public TaskDAO() {
    }

    public List<Task> getTasksByUserId(int userId) {
        return DBManager.getUserTasksById(userId);
    }

    public void addTask(int projectId, int numberOfPerformers, String text, int userId) {
        DBManager.addTask(projectId, numberOfPerformers, text, userId);
    }

    public void addTask(int projectId, int performersNumber, String taskText) {
        DBManager.addTask(projectId, performersNumber, taskText);
    }

    public List<Task> getTasksByProjectId(int projectId) {
        return DBManager.getTasksByProjectId(projectId);
    }

    public Task getTasksById(int taskId) {
        return DBManager.getTaskById(taskId);
    }
}
