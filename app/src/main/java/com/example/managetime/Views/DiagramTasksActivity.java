package com.example.managetime.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import java.util.List;

public class DiagramTasksActivity extends Activity {

    private List<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_tasks);
    }

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, DiagramTasksActivity.class);
        caller.startActivity(intent);
    }


}
