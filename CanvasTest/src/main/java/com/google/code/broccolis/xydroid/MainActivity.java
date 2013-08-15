package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    /*public void selectLevel(View view)
    {
        Intent intent = new Intent(this, SelectLevel.class);
        startActivity(intent);
    }*/

}