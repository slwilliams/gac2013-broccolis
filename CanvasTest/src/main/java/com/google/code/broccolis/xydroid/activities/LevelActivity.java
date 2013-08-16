package com.google.code.broccolis.xydroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.view.DrawView;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_WIDTH;

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

        setComponentSizes();

        Log.i(TAG, NAME + "created");
    }

    private void setComponentSizes()
    {
        Button button = (Button)findViewById(R.id.button11);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button12);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button13);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button21);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button22);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button23);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button31);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button32);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button33);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button41);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button42);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button43);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button61);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button62);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button63);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button71);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button72);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button73);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button81);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button82);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button83);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button91);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button92);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));

        button = (Button)findViewById(R.id.button93);
        button.setScaleX((float) SCREEN_WIDTH / (NEXUS_WIDTH));
        button.setScaleY((float) SCREEN_HEIGHT / (NEXUS_HEIGHT));
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