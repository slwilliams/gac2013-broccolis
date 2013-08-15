package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.code.broccolis.xydroid.ui.view.DrawView;

public class SelectLevel extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_level);
    }

    public void selectLevel(View view)
    {
        int lNumber = Integer.parseInt((String) ((Button) view).getText());
        Intent intent;

        switch (lNumber){
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