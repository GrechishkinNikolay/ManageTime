package com.example.managetime.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private ProgressDialog progressDialog;
    private FloatingActionButton addTaskFloatingButton;
    private FloatingActionButton addDurationFloatingButton;

    private static final String EXTRA_TASK = "AddTaskActivity.EXTRA_TASK";
    private Task task;

    public static void start(Activity caller, Task task){
        Intent intent = new Intent(caller, AddTaskActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        init();
    }

    private void init() {
        editTextTaskTitle = (EditText) findViewById(R.id.taskTitle);
        addTaskFloatingButton = (FloatingActionButton) findViewById(R.id.addTaskFloatingButton);
        addDurationFloatingButton = (FloatingActionButton) findViewById(R.id.addDurationFloatingButton);

        editTextTaskTitle.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        setTitle(getString(R.string.add_activity_title));

        if (getIntent().hasExtra(EXTRA_TASK)) {
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editTextTaskTitle.setText(task.title);
        } else {
            task = new Task();
        }
    }

    public void putFloatingButtons() {

    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
