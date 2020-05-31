package com.example.managetime.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.managetime.Model.DBHelper;
import com.example.managetime.Model.HomeModel;
import com.example.managetime.Presenter.HomePresenter;
import com.example.managetime.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements HomeViewContract {

    private HomePresenter presenter;

    private FloatingActionButton addTaskFloatingButton;
    private CalendarView calendar;
    private ListView tasksListView;
    List<String> tasks = Arrays.asList(new String[]{"Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей"});


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        calendar = findViewById(R.id.calendarView);
        tasksListView = findViewById(R.id.tasksListView);
        addTaskFloatingButton = (FloatingActionButton) findViewById(R.id.addButton);
        ConstraintLayout topConstrainLayout = findViewById(R.id.calendarPath);

        Date currentDate = Calendar.getInstance().getTime();
        long currentDateInMilliseconds = currentDate.getTime();

        addTaskFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
        calendar.setDate(currentDateInMilliseconds);

        showTasks(tasks);

        DBHelper dbHelper = new DBHelper(this);
        HomeModel model = new HomeModel(dbHelper);
        presenter = new HomePresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void showTasks(List<String> tasks) {
        ArrayAdapter<String> tasksListViewAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tasks);
        tasksListView.setAdapter(tasksListViewAdapter);
    }
}
