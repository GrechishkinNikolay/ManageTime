package com.example.managetime.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.Presenter.AdapterListTasks;
import com.example.managetime.Presenter.MainActivityPresenter;
import com.example.managetime.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements HomeViewContract {

    private MainActivityPresenter presenter;

    private FloatingActionButton addTaskFloatingButton;
    private CalendarView calendar;
    SimpleExpandableListAdapter expandableTaskListAdapter;
    RecyclerView tasksListView;

    private long selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        calendar = findViewById(R.id.calendarView);
        selectedDate = Calendar.getInstance().getTime().getTime();
        calendar.setDate(selectedDate);

        tasksListView = findViewById(R.id.tasksListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        tasksListView.setLayoutManager(linearLayoutManager);
        tasksListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final AdapterListTasks adapterListTasks = new AdapterListTasks();
        tasksListView.setAdapter(adapterListTasks);

        addTaskFloatingButton = (FloatingActionButton) findViewById(R.id.addButton);
        addTaskFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskActivity.start(MainActivity.this, null, selectedDate);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth);
                selectedDate = date.getTimeInMillis();
                presenter.setSelectedDate(selectedDate);
                List<Task> tasks = App.getInstance().getTaskDao().getTasksByStartTime(selectedDate).getValue();
                if (tasks != null) {
                    adapterListTasks.setItems(tasks);
                }
                Toast.makeText(MainActivity.this, String.valueOf(selectedDate), Toast.LENGTH_SHORT).show();
            }
        });

        presenter = new ViewModelProvider(this).get(MainActivityPresenter.class);
        presenter.getTasksLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapterListTasks.setItems(tasks);
            }
        });
        presenter.attachView(this);
    }
}
