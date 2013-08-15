package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_WIDTH;

public class MainActivity extends Activity
{
    private static final String NAME = "MainMenu ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_interface);

        setScreenSize();

        Log.i(TAG, NAME + "created");
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
        Log.i(TAG, NAME + "starting new game");

        Intent intent = new Intent(MainActivity.this, LevelActivity.class);

        startActivity(intent);
    }

    public void selectLevel(View view)
    {
        Log.i(TAG, NAME + "starting level selection screen");

        Intent intent = new Intent(MainActivity.this, SelectLevelActivity.class);

        startActivity(intent);
    }

    public void about(View view)
    {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}