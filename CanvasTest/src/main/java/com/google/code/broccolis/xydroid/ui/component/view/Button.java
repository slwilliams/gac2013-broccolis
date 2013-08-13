package com.google.code.broccolis.xydroid.ui.component.view;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Button
{
    private Point top;
    private Point bottom;
    private String text;

    public Button(Point top, Point bottom, String text)
    {
        this.top = top;
        this.bottom = bottom;
        this.text = text;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setTextSize(30f);
        paint.setColor(Color.BLACK);
        canvas.drawRect(top.x, top.y, bottom.x, bottom.y, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, top.x + 50, top.y + 40, paint);
    }

    public boolean isTouched(int touchX, int touchY)
    {
        return (touchX <= bottom.x && touchX >= top.x && touchY <= bottom.y && touchY >= top.y);
    }

    public String getText()
    {
        return text;
    }
}
