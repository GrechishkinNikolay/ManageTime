package com.example.managetime.Views;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private EditText editTextTaskTitle;
    private TextView textTimeAndDate;
    private FloatingActionButton addTaskFloatingButton;
    private FloatingActionButton addTimeFloatingButton;

    private static final String EXTRA_TASK = "AddTaskActivity.EXTRA_TASK";
    private static final String EXTRA_DATE = "AddTaskActivity.EXTRA_DATE";
    private Task task;
    private Date date;

    private String dateAndTimeString;
    private SimpleDateFormat formatter;

    public static void start(Activity caller, Task task, long date) {
        Intent intent = new Intent(caller, AddTaskActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }
        if (date != 0) {
            intent.putExtra(EXTRA_DATE, date);
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
        textTimeAndDate = (TextView) findViewById(R.id.textTimeAndDate);
        addTaskFloatingButton = (FloatingActionButton) findViewById(R.id.addTaskFloatingButton);
        addTimeFloatingButton = (FloatingActionButton) findViewById(R.id.addTimeFloatingButton);

        editTextTaskTitle = (EditText) findViewById(R.id.taskTitle);
        editTextTaskTitle.requestFocus();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setTitle(getString(R.string.add_activity_title));

        formatter = new SimpleDateFormat("d MMMM yyyy H:mm");

        if (getIntent().hasExtra(EXTRA_TASK)) {
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editTextTaskTitle.setText(task.title);

            date = new Date(task.startTime);
            dateAndTimeString = formatter.format(date);
            textTimeAndDate.setText(dateAndTimeString);

        } else {
            task = new Task();
            if (getIntent().hasExtra(EXTRA_DATE)) {
                date = new Date(getIntent().getLongExtra(EXTRA_DATE, 0));
                dateAndTimeString = formatter.format(date);
                textTimeAndDate.setText(dateAndTimeString);
            }
        }

        addTaskFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTaskTitle.getText().length() > 0) {
                    task.title = editTextTaskTitle.getText().toString();
                    task.isDone = false;
                    task.startTime = date.getTime();
                    if (getIntent().hasExtra(EXTRA_TASK)) {
                        App.getInstance().getTaskDao().updateTask(task);
                        Toast.makeText(AddTaskActivity.this, "Задача обновлена", Toast.LENGTH_SHORT).show();
                    } else {
                        App.getInstance().getTaskDao().insertTask(task);
                        Toast.makeText(AddTaskActivity.this, "Задача добавлена", Toast.LENGTH_SHORT).show();
                    }
                    editTextTaskTitle.setText("");
                    date.setHours(0);
                    date.setMinutes(0);
                    dateAndTimeString = formatter.format(date);
                    textTimeAndDate.setText(dateAndTimeString);
                }
            }
        });

        addTimeFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        date.setHours(hourOfDay);
        date.setMinutes(minute);
        dateAndTimeString = formatter.format(date);
        textTimeAndDate.setText(dateAndTimeString);
    }
}
