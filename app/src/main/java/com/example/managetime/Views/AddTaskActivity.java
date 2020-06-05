package com.example.managetime.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private TextView textTimeAndDate;
    private ProgressDialog progressDialog;
    private FloatingActionButton addTaskFloatingButton;
    private FloatingActionButton addDurationFloatingButton;

    private static final String EXTRA_TASK = "AddTaskActivity.EXTRA_TASK";
    private static final String EXTRA_DATE = "AddTaskActivity.EXTRA_DATE";
    private Task task;
    private Date date;

    private View view;

    public static void start(Activity caller, Task task, Date date) {
        Intent intent = new Intent(caller, AddTaskActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
            if (date != null) {
                intent.putExtra(EXTRA_DATE, date.getTime());
            }
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        init();
    }

/*    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull final Context context, @NonNull AttributeSet attrs) {

        view = LayoutInflater.from(context).inflate(R.layout.activity_add_task, null);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
//r will be populated with the coordinates of your view that area still visible.
                view.getWindowVisibleDisplayFrame(r);

                int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                    Toast.makeText(context, "fdg", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }*/

    private void init() {
        editTextTaskTitle = (EditText) findViewById(R.id.taskTitle);
        textTimeAndDate = (TextView) findViewById(R.id.textTimeAndDate);
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
//        textTimeAndDate.setText(date.toString());

        addTaskFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTaskTitle.getText().length() > 0) {
                    task.title = editTextTaskTitle.getText().toString();
                    task.isDone = false;
                    if (getIntent().hasExtra(EXTRA_TASK)) {
                        App.getInstance().getTaskDao().updateTask(task);
                        Toast.makeText(AddTaskActivity.this, "Задача обновлена", Toast.LENGTH_SHORT).show();
                    } else {
                        App.getInstance().getTaskDao().insertTask(task);
                        Toast.makeText(AddTaskActivity.this, "Задача добавлена", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });
    }
}
