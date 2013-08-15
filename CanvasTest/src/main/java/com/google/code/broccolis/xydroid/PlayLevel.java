package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.code.broccolis.xydroid.ui.view.DrawView;

public class PlayLevel extends Activity
{
    private String[] items = {"x", "-x", "x^2", "sin(x)", "xsin(x)",  "Custom", "Clear"};
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    DrawView drawView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);

        setContentView(R.layout.main_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        ((FrameLayout) findViewById(R.id.content_frame)).addView(drawView);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.list_layout, items));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void onPause()
    {
        super.onPause();
        drawView.setPaused();
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
        switch(position)
        {
            case 0: drawView.addFunction("x");
                    break;
            case 1: drawView.addFunction("-x");
                    break;
            case 2: drawView.addFunction("(x*0.05)^2");
                    break;
            case 3: drawView.addFunction("60*sin(x*0.02)");
                    break;
            case 4: drawView.addFunction("60*sin(x*0.1)*(x*0.007)");
                    break;
            case 5: drawView.addCustomFunction();
                    break;
            case 6: drawView.clear();
        }
        drawerList.setItemChecked(position, true);
        setTitle(items[position]);
        drawerLayout.closeDrawer(drawerList);
    }
}