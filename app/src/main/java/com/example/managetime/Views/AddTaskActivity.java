package com.example.managetime.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managetime.R;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        init();
    }

    private void init() {
        editTextTaskTitle = (EditText) findViewById(R.id.taskTitle);

        editTextTaskTitle.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

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
