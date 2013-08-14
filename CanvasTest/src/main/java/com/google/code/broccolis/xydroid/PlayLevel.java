package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.google.code.broccolis.xydroid.ui.view.DrawView;


public class PlayLevel extends Activity
{
    private DrawView drawView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        DrawView.height = height;
        DrawView.width = width;

        drawView = new DrawView(this);

        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
    }
}