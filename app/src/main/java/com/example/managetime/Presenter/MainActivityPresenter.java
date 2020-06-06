package com.example.managetime.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;

import java.sql.Date;
import java.util.List;

public class MainActivityPresenter extends ViewModel {

    private Date selectedDate;

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
        tasksLiveData = App.getInstance().getTaskDao().getTasksByStartTime(selectedDate);
    }

    private LiveData<List<Task>> tasksLiveData;

    public LiveData<List<Task>> getTasksLiveData() {
        return tasksLiveData;
    }
}
