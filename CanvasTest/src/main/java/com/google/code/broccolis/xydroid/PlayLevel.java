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
    private String[] items = {"test",  "test2"};
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    DrawView drawView;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        DrawView.height = height;
        DrawView.width = width;
        drawView = new DrawView(this);

        drawView.setBackgroundColor(Color.WHITE);

        setContentView(R.layout.main_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        ((FrameLayout) findViewById(R.id.content_frame)).addView(drawView);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.list_layout, items));
        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());






    }

    public void onPause()
    {
        drawView.setPaused();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        //Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
      //  args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);
//
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
       /* fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();*/

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(items[position]);
        drawerLayout.closeDrawer(drawerList);
    }

}