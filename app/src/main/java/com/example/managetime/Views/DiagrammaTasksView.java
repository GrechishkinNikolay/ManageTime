package com.example.managetime.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

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

    private void initClock() {
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 50;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        canvas.drawColor(Color.DKGRAY);
        drawCircle(canvas);
 /*       drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas)*/;

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, paint);
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
