package com.example.managetime.Model.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.managetime.Model.dao.PatternDao;
import com.example.managetime.Model.dao.ProjectDao;
import com.example.managetime.Model.dao.TaskDao;
import com.example.managetime.Model.dto.Pattern;
import com.example.managetime.Model.dto.Project;
import com.example.managetime.Model.dto.Task;

@Database(entities = {Task.class, Project.class, Pattern.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();
    public abstract PatternDao patternDao();
}
