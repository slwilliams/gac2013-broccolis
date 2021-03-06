package com.google.code.broccolis.xydroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.code.broccolis.xydroid.R;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;

public class SelectLevelActivity extends Activity
{
    private static final String NAME = "LevelSelectionScreen ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_level);
        TextView textview = (TextView) findViewById(R.layout.select_level);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/noteworthy.otf");

//        textview.setTypeface(face, Typeface.BOLD);

        Log.i(TAG, NAME + "created");
    }

    public void selectLevel(View view)
    {
        int level = Integer.parseInt((String) ((Button) view).getText());

        Log.i(TAG, NAME + "starting level " + level);

        Intent intent = new Intent(SelectLevelActivity.this, LevelActivity.class);
        intent.putExtra("level", Integer.toString(level));
        startActivity(intent);
    }
}