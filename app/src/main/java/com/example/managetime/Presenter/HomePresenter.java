package com.example.managetime.Presenter;

import com.example.managetime.Model.HomeModel;
import com.example.managetime.Views.HomeViewContract;
import com.example.managetime.Views.MainActivity;

import java.util.List;

public class HomePresenter {

    private HomeViewContract view;
    private final HomeModel model;


    public HomePresenter(HomeModel model) {
        this.model = model;
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
/*        model.loadUsers(new UsersModel.LoadUserCallback() {
            @Override
            public void onLoad(List<User> users) {
                view.showUsers(users);
            }
        });*/
    }
}
