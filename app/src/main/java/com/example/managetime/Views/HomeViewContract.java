package com.example.managetime.Views;

import com.example.managetime.Model.dto.Task;

import java.util.List;

public interface HomeViewContract {
    void showTasks(List<String> tasks);
}
