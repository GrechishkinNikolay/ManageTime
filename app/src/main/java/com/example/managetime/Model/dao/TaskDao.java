package com.example.managetime.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.managetime.Model.dto.Task;

import java.sql.Date;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasksLiveData();

    /*@Query("SELECT * FROM task WHERE date(startTime) = date(:date)")
    List<Task>  getTasksByStartTime(Date date);*/

    @Query("SELECT * FROM task WHERE cast(date(startTime) as integer) = :date")
    List<Task>  getTasksByStartTime(@TypeConverters({DateConverter.class}) Date date);

}
