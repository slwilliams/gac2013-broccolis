package com.google.code.broccolis.xydroid.ui.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.code.broccolis.xydroid.activities.LevelActivity;
import com.google.code.broccolis.xydroid.ui.component.CustomKeyboard;
import com.google.code.broccolis.xydroid.ui.component.model.Level1;
import com.google.code.broccolis.xydroid.ui.component.model.Level2;
import com.google.code.broccolis.xydroid.ui.component.view.FunctionView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;

public class DrawView extends View
{
    private static final String NAME = "DrawView ";
    private static final int jumpHeight = parseNexusY(100);
    private static final int jumpSpeed = parseNexusY(15);
    private static int gravity = 3;
    private double delta = 0.3;
    private final double SHAKE_DELTA = 2;
    private boolean isPaused = false;
    private boolean waitForFunctionTap = false;
    private String functionString = null;
    private int jumpBase = 400;
    private float xAcc = 0;
    private boolean jumping = false;
    private boolean falling = false;
    private Paint paint = new Paint();
    private Level level;
    private Player player;
    private Context context;
    private ArrayList<FunctionView> functions = new ArrayList<FunctionView>();
    private EditText alertDialogInput;
    private AlertDialog alertDialog;
    private double then;
    private CustomKeyboard keyboard;
    private boolean finished;

    public boolean off = false;

    public DrawView(Context context, int levelNum)
    {
        super(context);
        //keyboard = new CustomKeyboard((Activity) context, R.id.keyboardview, R.xml.func_keyboard);
        switch (levelNum)
        {
            case 1:
                level = new Level1(getResources());
                break;
            case 2:
                level = new Level2(getResources());
                break;
        }

        paint.setAntiAlias(true);
        paint.setSubpixelText(true);

        player = new Player(100f / NEXUS_WIDTH, 650f / NEXUS_HEIGHT, Color.BLACK, getResources());
        this.context = context;

        alertDialogInput = new EditText(context);
        createAlert();
        //keyboard.registerEditText(alertDialogInput);

        initAccelerometer();

        Log.i(TAG, NAME + "created");
    }

    public void clear()
    {
        functions.clear();
    }

    public void addFunction(String function)
    {
        functionString = function;
        Toast.makeText(context, "Tap to finish function", Toast.LENGTH_LONG).show();
        waitForFunctionTap = true;
    }

    public void addCustomFunction()
    {
        Log.i(TAG, NAME + "adding function");

        isPaused = true;
        xAcc = 0;

        if (!alertDialog.isShowing())
        {
            alertDialog.show();
        }
    }

    public boolean isAwaitingFunctionTap()
    {
        return waitForFunctionTap;
    }

    public void setPaused()
    {
        isPaused = true;
        xAcc = 0;
    }

    public void resume()
    {
        isPaused = false;
    }

    public void undo()
    {
        functions.remove(functions.size() - 1);
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
                if (!isPaused)
                {
                    xAcc = event.values[1];
                    double zAcc = (double) event.values[2];


                    if (Math.abs(zAcc - then) > SHAKE_DELTA)
                    {
                        if (!falling)
                        {
                            jumpBase = player.getY();
                            jumping = true;
                        }
                    }
                    then = zAcc;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {
            }

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        if (!collision(player, new Point((int) xAcc * 2, 0)))
        {
            player.move((int) xAcc * 2, 0);
        }

        doPhysics();

        for (FunctionView f : functions)
        {
            f.draw(canvas, paint);
        }
        if (!off)
        {
            level.draw(canvas, paint);
        }
        if (!off)
        {
            player.draw(canvas, paint);
        }

        postInvalidateOnAnimation();
    }

    public void off()
    {
        off = true;
    }

    public void doPhysics()
    {
        if (!collision(player, new Point(0, gravity)))
        {
            player.move(0, gravity);
            gravity += delta;

            falling = true;
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

    public synchronized boolean collision(Player player, Point moveAmount)
    {
        if (level.goalReached(player, moveAmount) && !finished)
        {
            if (level instanceof Level1)
            {
                Intent intent = new Intent(context, LevelActivity.class);
                intent.putExtra("level", 2);
                context.startActivity(intent);
                ((Activity) context).finish();
                finished = true;
            }
        }

        if (level.collidesWith(player, moveAmount))
        {
            falling = false;
            gravity = 5;
            return true;
        }
        else
        {
            for (FunctionView f : functions)
            {
                if (f.collidesWith(player, moveAmount))
                {
                    falling = false;
                    gravity = 5;
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

        if (waitForFunctionTap)
        {
            int tapX = (int) eventX;
            int origin, maxX;
            if (tapX > player.getX())
            {
                origin = player.getX() + 40;
            }
            else
            {
                origin = player.getX() - 40;
            }
            maxX = tapX - origin;
            functions.add(new FunctionView(functionString, new PointOnScreen(parseXToFloat(origin), parseYToFloat(player.getY() - 20)), maxX));
            waitForFunctionTap = false;
            isPaused = false;
        }
        else if (!falling)
        {
            if (eventX >= 75)
            {
                jumping = true;
                jumpBase = player.getY();
            }
        }


        return true;
    }

    private void createAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Enter Function");
        builder.setMessage("e.g. x^2 + 2x + 5");

        builder.setView(alertDialogInput);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                functionString = alertDialogInput.getText().toString();
                Toast.makeText(context, "Tap to finish function", Toast.LENGTH_LONG).show();
                waitForFunctionTap = true;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                isPaused = false;
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            public void onCancel(DialogInterface dialog)
            {
                isPaused = false;
            }
        });

        alertDialog = builder.create();
    }
}