package com.example.managetime.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.managetime.App;
import com.example.managetime.Model.HomeModel;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.Views.HomeViewContract;

import java.util.List;

public class HomePresenter extends ViewModel {

    private HomeViewContract view;

    private LiveData<List<Task>> tasksLiveData = App.getInstance().getTaskDao().getAllTasksLiveData();

    public LiveData<List<Task>> getTasksLiveData() {
        return tasksLiveData;
    }

    public HomePresenter() {
    }

    public void attachView(HomeViewContract usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }


    public void viewIsReady() {
        loadTasks();
    }

    public void loadTasks() {

    }
}
