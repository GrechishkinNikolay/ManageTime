package com.example.managetime.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.managetime.Model.dto.Project;
import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM project")
    List<Project> getAllProjects();

    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAllProjectsLiveData();

    @Query("SELECT * FROM project WHERE name = :name ")
    Project  getProjectByName(String name);

    @Query("SELECT * FROM project WHERE id = :id LIMIT 1")
    Project getProjectById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(Project project);

    @Update
    void updateProject(Project project);

    @Delete
    void deleteProject(Project project);
}
