package com.example.managetime.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.managetime.Model.dto.Pattern;

import java.sql.Date;
import java.util.List;

@Dao
public interface PatternDao {

    @Query("SELECT * FROM pattern")
    List<Pattern> getAllPatterns();

    @Query("SELECT * FROM pattern")
    LiveData<List<Pattern>> getAllPatternsLiveData();

    @Query("SELECT * FROM pattern WHERE id = :id LIMIT 1")
    Pattern getTaskById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTask(Pattern pattern);

    @Update
    public void updateTask(Pattern pattern);

    @Delete
    public void deleteTask(Pattern pattern);

}
