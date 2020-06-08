package com.example.managetime.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeViewContract {

    private MainActivityPresenter presenter;

    private FloatingActionButton addTaskFloatingButton;
    private CalendarView calendar;
    SimpleExpandableListAdapter expandableTaskListAdapter;
    RecyclerView tasksListView;
    private boolean firstStart = true;

    private long selectedDateMill;
    private Date date;

    private static Date dateForDiagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public static Date getDateForDiagram() {
        return dateForDiagram;
    }

    private void init() {
        calendar = findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (firstStart) {
            date = new Date(Calendar.getInstance().getTime().getTime());
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            selectedDateMill = date.getTime();
            dateForDiagram = date;
            calendar.setDate(selectedDateMill);
        }

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
                AddTaskActivity.start(MainActivity.this, null, selectedDateMill);
            }
        });

        presenter = new ViewModelProvider(this).get(MainActivityPresenter.class);
        presenter.setSelectedDate(date);
        presenter.getTasksLiveData().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapterListTasks.setItems(tasks);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                GregorianCalendar gregorianCalendarDate = new GregorianCalendar(year, month, dayOfMonth,0,0);
                selectedDateMill = gregorianCalendarDate.getTimeInMillis();

                date = new Date(selectedDateMill);
                dateForDiagram = date;
                presenter.setSelectedDate(date);
                presenter.getTasksLiveData().observe(MainActivity.this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        adapterListTasks.setItems(tasks);
                    }
                });
            }
        });

        addTaskFloatingButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                List<Task> tasks = App.getInstance().getTaskDao().getTasksByDay(date);
                DiagramTasksActivity.start(MainActivity.this);
                return true;
            }
        });

        firstStart = false;
    }
}
