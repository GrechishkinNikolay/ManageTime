package com.example.managetime.Presenter;

import com.example.managetime.Views.MainActivity;

public class HomePresenter {

    private MainActivity view;
    private final UsersModel model;

    public UsersPresenter(UsersModel model) {
        this.model = model;
    }

    public void attachView(UsersContractView usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }


    public void viewIsReady() {
        loadUsers();
    }
}
