package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import com.google.code.broccolis.xydroid.ui.view.DrawView;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_interface);
    }

    public void newGame(View view)
    {
        Intent intent = new Intent(this, PlayLevel.class);
        startActivity(intent);
    }

   /* public void startLevel(View view)
    {
        int buttonId = view.getId();
        switch(buttonId)
        {
            case R.id.buttonLevel1 etc....
        }
    }*/
}