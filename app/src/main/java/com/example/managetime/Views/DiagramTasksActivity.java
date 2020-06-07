package com.example.managetime.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.managetime.Model.dto.Task;

import java.util.ArrayList;
import java.util.List;

public class DiagramTasksActivity extends AppCompatActivity {

    private static final String EXTRA_TASKS = "DiagramTasksActivity.EXTRA_TASKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_tasks);
    }

    public static void start(Activity caller, List<Task> tasks) {
        Intent intent = new Intent(caller, DiagramTasksActivity.class);
        if (tasks != null) {
            intent.putParcelableArrayListExtra(EXTRA_TASKS, (ArrayList<? extends Parcelable>) tasks);
        }
        caller.startActivity(intent);
    }
}
