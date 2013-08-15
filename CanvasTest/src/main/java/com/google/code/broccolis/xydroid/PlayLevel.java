package com.google.code.broccolis.xydroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.code.broccolis.xydroid.ui.view.DrawView;

public class PlayLevel extends Activity
{
    private String[] items = {"x^2", "test2"};
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);

        drawView.setBackgroundColor(Color.WHITE);

        setContentView(R.layout.main_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        ((FrameLayout) findViewById(R.id.content_frame)).addView(drawView);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.list_layout, items));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public void onPause()
    {
        drawView.setPaused();
    }

    private void selectItem(int position)
    {
        if (position == 0)
        {
            drawView.addFunction("(x*0.05)^2");
        }
        if (position == 1)
        {
            drawView.addCustomFunction();
        }
        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(items[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id)
        {
            selectItem(position);
        }
    }

}