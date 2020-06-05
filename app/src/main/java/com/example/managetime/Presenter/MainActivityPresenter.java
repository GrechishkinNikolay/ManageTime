package com.example.managetime.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.Views.HomeViewContract;

import java.sql.Date;
import java.util.List;

public class MainActivityPresenter extends ViewModel {

    private long selectedDate;
    private HomeViewContract view;

    public void setSelectedDate(long selectedDate) {
        this.selectedDate = selectedDate;
    }

//        private LiveData<List<Task>> tasksLiveData = App.getInstance().getTaskDao().getAllTasksLiveData();
    private LiveData<List<Task>> tasksLiveData = App.getInstance().getTaskDao().getTasksByStartTime(selectedDate);

    public LiveData<List<Task>> getTasksLiveData() {
        return tasksLiveData;
    }

    public void attachView(HomeViewContract usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }
}
