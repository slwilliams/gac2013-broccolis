package com.google.code.broccolis.xydroid.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.code.broccolis.xydroid.ui.component.view.Button;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.ui.component.view.FunctionView;

import java.util.ArrayList;


public class DrawView extends View
{
    Paint paint = new Paint();

    public static int height = 0;
    public static int width = 0;

    boolean leftDown = false;
    boolean rightDown = false;

    Player player;
    ArrayList<WallView> walls = new ArrayList<WallView>();
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<FunctionView> functions = new ArrayList<FunctionView>();

    boolean jumping = false;
    int jumpBase = 400;
    int jumpHeight = 100;
    int jumpSpeed = 15;

    int gravity = 5;

    public DrawView(Context context)
    {
        super(context);
        paint.setAntiAlias(true);
        player = new Player(200, 400, Color.BLACK);
        initWorld();
    }

    private void initWorld()
    {
        walls.add(new WallView(new Point(0, 500), new Point(1300, 525), Color.RED));
        walls.add(new WallView(new Point(500, 400), new Point(1300, 425), Color.BLUE));
        walls.add(new WallView(new Point(0, 300), new Point(300, 325), Color.GREEN));
        walls.add(new WallView(new Point(700, 200), new Point(1300, 225), Color.YELLOW));

        buttons.add(new Button(new Point(25, 550), new Point(225, 625), "left"));
        buttons.add(new Button(new Point(300, 550), new Point(500, 625), "right"));
        buttons.add(new Button(new Point(1000, 550), new Point(1200, 625), "jump"));

        functions.add(new FunctionView("50*sin(x*0.1)", new Point(0,300), 600));
    }

    double val = 0.1;

    public void onDraw(Canvas canvas)
    {
        if (leftDown)
        {
            if (!collision(player.getX() - 5, player.getY()))
            {
                player.move(-5, 0);
            }
        }

        if (rightDown)
        {
            if (!collision(player.getX() + 5, player.getY()))
            {
                player.move(5, 0);
            }
        }

        doPhysics();
        walls.get(2).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

        for (WallView w : walls)
        {
            w.draw(canvas, paint);
        }

        for (Button b : buttons)
        {
            b.draw(canvas, paint);
        }

        for(FunctionView f : functions)
        {
            f.draw(canvas, paint);
        }

        player.draw(canvas, paint);
        postInvalidateOnAnimation();
    }


    public void doPhysics()
    {
        if (!collision(player.getX(), player.getY() + gravity))
        {
            player.move(0, gravity);
        }

        if (jumping && Math.abs(player.getY() - jumpBase) > jumpHeight)
        {
            jumping = false;
        }

        if (jumping && !collision(player.getX(), player.getY() - jumpSpeed))
        {
            player.move(0, -jumpSpeed);
        }

    }

    public boolean collision(int newPlayerX, int newPlayerY)
    {
        for (WallView w : walls)
        {
            if (w.collidesWith(newPlayerX, newPlayerY))
            {
                return true;
            }
        }
        for (FunctionView f : functions)
        {
            if(f.collidesWith(newPlayerX, newPlayerY))
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        float eventX;
        float eventY;
        int actionEvent;


        int action = event.getAction();
        if (event.getPointerCount() > 1)
        {
            actionEvent = event.getActionMasked();
            int actionPointerId = event.getActionIndex();
            Log.i("err", Integer.toString(actionPointerId));
            int index = event.findPointerIndex(actionPointerId);

            // Gets its coordinates
            eventX = event.getX(index);
            eventY = event.getY(index);
        }
        else
        {
            eventX = event.getX();
            eventY = event.getY();
            actionEvent = event.getAction();
            if (actionEvent == MotionEvent.ACTION_UP)
            {
                leftDown = rightDown = false;
            }
        }

        for (Button b : buttons)
        {
            if (b.isTouched((int) eventX, (int) eventY))
            {
                if (b.getText().equals("left"))
                {
                    if (actionEvent == MotionEvent.ACTION_DOWN)
                    {
                        leftDown = true;
                    }
                    else if (actionEvent == MotionEvent.ACTION_UP)
                    {
                        leftDown = false;
                    }
                }

                if (b.getText().equals("right"))
                {
                    if (actionEvent == MotionEvent.ACTION_DOWN)
                    {
                        rightDown = true;
                    }
                    else if (actionEvent == MotionEvent.ACTION_UP)
                    {
                        rightDown = false;
                    }
                }
                if (b.getText().equals("jump"))
                {
                    jumping = true;
                    jumpBase = player.getY();
                }
            }
        }

        return true;
    }
}