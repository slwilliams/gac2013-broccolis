package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.google.code.broccolis.xydroid.util.DeviceDependantVariables.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.DeviceDependantVariables.SCREEN_WIDTH;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_interface);
        setScreenSize();
    }

    private void setScreenSize()
    {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        SCREEN_WIDTH = screenSize.x;
        SCREEN_HEIGHT = screenSize.y;
    }

    public void newGame(View view)
    {
        Intent intent = new Intent(MainActivity.this, PlayLevel.class);
        startActivity(intent);
    }

    public void selectLevel(View view)
    {
        Intent intent = new Intent(MainActivity.this, SelectLevel.class);
        startActivity(intent);
    }
}