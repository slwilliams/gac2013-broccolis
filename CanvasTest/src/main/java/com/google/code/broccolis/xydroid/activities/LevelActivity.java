package com.google.code.broccolis.xydroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.view.DrawView;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;

public class LevelActivity extends Activity
{
    private static final String NAME = "Level ";
    private String[] items = {"x", "-x", "x^2", "sin(x)", "xsin(x)", "xcos(x)", "tan(x)", "Custom", "Clear", "Undo", "off"};
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent called = getIntent();
        int level = Integer.parseInt(called.getExtras().get("level").toString());

        drawView = new DrawView(this, level);

        setContentView(R.layout.main_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View view, float v)
            {
            }

            @Override
            public void onDrawerOpened(View view)
            {
                drawView.setPaused();
            }

            @Override
            public void onDrawerClosed(View view)
            {
                if (!drawView.isAwaitingFunctionTap())
                {
                    drawView.resume();
                }
            }

            @Override
            public void onDrawerStateChanged(int i)
            {

            }
        });
        drawerList = (ListView) findViewById(R.id.left_drawer);

        ((FrameLayout) findViewById(R.id.content_frame)).addView(drawView);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.list_layout, items));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        Log.i(TAG, NAME + "created");
    }

    @Override
    public void onPause()
    {
        Log.i(TAG, NAME + "paused");

        super.onPause();

        drawView.setPaused();
    }

    public void onResume()
    {
        super.onResume();
        drawView.resume();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id)
        {
            selectItem(position);
        }
    }

    private void selectItem(int position)
    {
        switch (position)
        {
            case 0:
                drawView.addFunction("x");
                break;
            case 1:
                drawView.addFunction("-x");
                break;
            case 2:
                drawView.addFunction("(x*0.05)^2");
                break;
            case 3:
                drawView.addFunction("60*sin(x*0.02)");
                break;
            case 4:
                drawView.addFunction("60*sin(x*0.1)*(x*0.007)");
                break;
            case 5:
                drawView.addFunction("60*cos(x*0.1)*(x*0.007)");
                break;
            case 6:
                drawView.addFunction("tan(x*0.05)");
                break;
            case 7:
                drawView.addCustomFunction();
                break;
            case 8:
                drawView.clear();
                break;
            case 9:
                drawView.undo();
                break;
            case 10:
                drawView.off();
        }
        drawerList.setItemChecked(position, true);
        setTitle(items[position]);
        drawerLayout.closeDrawer(drawerList);
    }
}