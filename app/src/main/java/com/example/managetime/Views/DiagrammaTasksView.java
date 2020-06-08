package com.example.managetime.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiagrammaTasksView extends View {

    private int height, width = 0;
    private int padding = 0;
    private int fontSize = 0;
    private int numeralSpacing = 0;
    private int handTruncation, hourHandTruncation = 0;
    private int radius = 0;
    private Paint paint;
    private boolean isInit;
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();
    private List<Task> tasks;
    private Calendar calendar;
    private float hour;

    private int minute;
    private int hourOfDay;

    private void initClock() {
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 50;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        Date date = MainActivity.getDateForDiagram();
        tasks = App.getInstance().getTaskDao().getTasksByDay(date);
        calendar = Calendar.getInstance();
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        drawCircle(canvas);
        drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas);
        drawTasks(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawTasks(Canvas canvas) {
        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorTask));
        paint.setAlpha(50);

        oval.set(width / 2 - (radius - 30), height / 2 - (radius - 30), width / 2 + (radius - 30),
                height / 2 + (radius - 30));

        long currentTime = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR, 12);
        long endTime = calendar.getTimeInMillis();

        for (Task task : tasks) {
            Date taskDate = new Date(task.startTime);
            drawTask(canvas, oval, task.startTime, task.duration);
            if (currentTime <= task.startTime && task.startTime < endTime) {
                drawTask(canvas, oval, task.startTime, task.duration);
            }
        }
    }

    private void drawTask(Canvas canvas, RectF oval, long startTime, long duration) {
        Date startTimeDate = new Date(startTime);
        int hours = startTimeDate.getHours();
        int minut = hours * 60 + startTimeDate.getMinutes();

        canvas.drawArc(oval, minut / 2 - 90, duration / 60000 / 2, true, paint);
    }

    private void drawCenter(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(width / 2, height / 2, radius / 10 , paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, paint);
    }

    private void drawHands(Canvas canvas) {
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        hourOfDay = (int) hour;
        minute = calendar.get(Calendar.MINUTE);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + calendar.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas, calendar.get(Calendar.SECOND), false);
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2, height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),
                paint);
    }

    private void drawNumeral(Canvas canvas) {
        paint.setTextSize(fontSize);

        for (int number : numbers) {
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
            canvas.drawText(tmp, x, y, paint);
        }
    }
    
    public DiagrammaTasksView(Context context) {
        super(context);
    }

    public DiagrammaTasksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiagrammaTasksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
