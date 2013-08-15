package com.google.code.broccolis.xydroid.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.google.code.broccolis.xydroid.ui.component.model.Level1;
import com.google.code.broccolis.xydroid.ui.component.model.Level2;

import com.google.code.broccolis.xydroid.ui.component.view.Button;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.ui.component.view.FunctionView;

import java.util.ArrayList;


public class DrawView extends View
{
    Paint paint = new Paint();

    public static int height = 0;
    public static int width = 0;

    Player player;

    Level level = new Level2(getResources());

    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<FunctionView> functions = new ArrayList<FunctionView>();

    float xAcc = 0;

    boolean jumping = false;
    int jumpBase = 400;
    int jumpHeight = 100;
    int jumpSpeed = 15;
    private Context context;

    int gravity = 5;

    public DrawView(Context context)
    {
        super(context);
        paint.setAntiAlias(true);
        player = new Player(100, 450, Color.BLACK, getResources());
        initWorld();
        this.context = context;
        initAccelerometer();
    }

    public void initAccelerometer()
    {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                xAcc = event.values[1];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void initWorld()
    {
        buttons.add(new Button(new Point(25, 25), new Point(200, 75), "Functions"));
        functions.add(new FunctionView("10*sin(x*0.05)*(0.01*x)", new Point(0,300), 600, width, height));
    }

    public void onDraw(Canvas canvas)
    {
        if (!collision(player, new Point((int)xAcc*2, 0)))
        {
            player.move((int)xAcc*2, 0);
        }

        doPhysics();
        level.draw(canvas, paint);

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
        if (!collision(player, new Point(0, gravity)))
        {
            player.move(0, gravity);
        }

        if (jumping && Math.abs(player.getY() - jumpBase) > jumpHeight)
        {
            jumping = false;
        }

        if (jumping)
        {
            if (!collision(player, new Point(0, -jumpSpeed)))
            {
                player.move(0, -jumpSpeed);
            }
            else
            {
                jumping = false;
            }
        }
    }

    public boolean collision(Player player, Point moveAmount)
    {
        if (level.collidesWith(player, moveAmount))
        {
            return true;
        }
        else
        {
            for (FunctionView f : functions)
            {
                if(f.collidesWith(player, moveAmount))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float eventX;
        float eventY;

        if (event.getPointerCount() > 1)
        {
            int actionPointerId = event.getActionIndex();
            int index = event.findPointerIndex(actionPointerId);

            eventX = event.getX(index);
            eventY = event.getY(index);
        }
        else
        {
            eventX = event.getX();
            eventY = event.getY();
        }

        for (Button b : buttons)
        {
            if (b.isTouched((int) eventX, (int) eventY))
            {
                if(b.getText().toLowerCase().equals("functions"))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                    alert.setTitle("Enter Function");
                    alert.setMessage("e.g. x^2 + 2x + 5");

                    final EditText input = new EditText(getContext());
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            String value = input.getText().toString();
                            functions.add(new FunctionView(value, new Point(600, 300), 800, width, height));
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton) { }
                    });

                    alert.show();
                }
            }
            else
            {
                jumping = true;
                jumpBase = player.getY();
            }
        }

        return true;
    }
}