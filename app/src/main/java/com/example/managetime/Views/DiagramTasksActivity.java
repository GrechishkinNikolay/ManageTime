package com.example.managetime.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.managetime.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiagramTasksActivity extends Activity {

    private SeekBar seekBar;
    private TextView rangeTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_tasks);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        rangeTimeTextView = (TextView) findViewById(R.id.rangeTime);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 360) {
                    seekBar.setProgress(360);
                    progress = 360;
                }
                if (progress > 1080) {
                    seekBar.setProgress(1080);
                    progress = 1080;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
                Date date = new Date();
                date.setHours((progress - 360) / 60);
                date.setMinutes((progress - 360) % 60);

//                Date endDate = new Date(task.startTime + task.duration);
                String startDateAndTimeString = formatter.format(date);
//                String endDateAndTimeString = formatter.format(endDate);
                rangeTimeTextView.setText(startDateAndTimeString + "-" + startDateAndTimeString + " ");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, DiagramTasksActivity.class);
        caller.startActivity(intent);
    }


}
