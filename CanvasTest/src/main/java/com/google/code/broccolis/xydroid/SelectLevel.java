package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent = new Intent(this, PlayLevel.class);
        startActivity(intent);
    }

}