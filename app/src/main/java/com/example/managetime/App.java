package com.example.managetime;

import android.app.Application;

import androidx.room.Room;

import com.example.managetime.Model.DB.AppDataBase;
import com.example.managetime.Model.dao.PatternDao;
import com.example.managetime.Model.dao.ProjectDao;
import com.example.managetime.Model.dao.TaskDao;

public class App extends Application {

    private AppDataBase dataBase;
    private TaskDao taskDao;
    private ProjectDao projectDao;
    private PatternDao patternDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"app-db-name")
                .allowMainThreadQueries()
                .build();

        taskDao = dataBase.taskDao();
        projectDao = dataBase.projectDao();
        patternDao = dataBase.patternDao();
    }

    public AppDataBase getDataBase() {
        return dataBase;
    }
    public TaskDao getTaskDao() {
        return taskDao;
    }
    public ProjectDao getProjectDao() {
        return projectDao;
    }
    public PatternDao getPatternDao() {
        return patternDao;
    }
}
