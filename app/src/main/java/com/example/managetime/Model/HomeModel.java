package com.example.managetime.Model;

import com.example.managetime.Model.dto.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeModel {

    private final DBHelper dbHelper;

    public HomeModel(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<Task> getTasksByDay(String date) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Задача 1", "Описание задачи 1", "", "", "", false, "", null));
        tasks.add(new Task(2, "Задача 2", "Описание задачи 2", "", "", "", false, "", null));
        tasks.add(new Task(3, "Задача 3", "Описание задачи 3", "", "", "", false, "", null));
        return tasks;
    }
}
