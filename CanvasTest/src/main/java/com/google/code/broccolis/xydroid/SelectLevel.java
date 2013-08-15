package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;

public class SelectLevel extends Activity
{
    private static final String NAME = "LevelSelectionScreen ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_level);

        Log.i(TAG, NAME + "created");
    }

    public void selectLevel(View view)
    {
        int lNumber = Integer.parseInt((String) ((Button) view).getText());
        Intent intent;

        Log.i(TAG, NAME + "starting level " + lNumber);

        switch (lNumber)
        {
            case 1:
                intent = new Intent(this, PlayLevel.class);
                startActivity(intent);
                break;

            case 2:
                //intent = new Intent(this, class);
                //startActivity(intent);
                break;
        }

    }

}