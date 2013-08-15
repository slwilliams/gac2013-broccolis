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
import android.widget.Toast;

import com.google.code.broccolis.xydroid.ui.component.model.Level2;
import com.google.code.broccolis.xydroid.ui.component.view.FunctionView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;

public class DrawView extends View
{
    private static final int jumpHeight = 500;
    private static final int jumpSpeed = 15;
    private static final int gravity = 5;
    private final double SHAKE_DELTA = 2;
    private boolean isPaused = false;
    private boolean waitForFunctionTap = false;
    private String functionString = null;
    private int jumpBase = 400;
    private float xAcc = 0;
    private boolean jumping = false;
    private boolean falling = false;
    private Paint paint = new Paint();
    private Level level = new Level2(getResources());
    private Player player;
    private Context context;
    private ArrayList<FunctionView> functions = new ArrayList<FunctionView>();
    private EditText alertDialogInput;
    private AlertDialog alertDialog;
    private double then;

    public DrawView(Context context)
    {
        super(context);

        paint.setAntiAlias(true);
        paint.setSubpixelText(true);

        player = new Player(100f / NEXUS_WIDTH, 650f / NEXUS_HEIGHT, Color.BLACK, getResources());
        this.context = context;

        alertDialogInput = new EditText(context);
        createAlert();

        initAccelerometer();
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
        isPaused = true;
        xAcc = 0;

        if (!alertDialog.isShowing())
        {
            alertDialog.show();
        }
    }

    public void setPaused()
    {
        isPaused = true;
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
        level.draw(canvas, paint);

        for (FunctionView f : functions)
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

    public boolean collision(Player player, Point moveAmount)
    {
        if (level.collidesWith(player, moveAmount))
        {
            falling = false;
            return true;
        }
        else
        {
            for (FunctionView f : functions)
            {
                if (f.collidesWith(player, moveAmount))
                {
                    falling = false;
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

        builder.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            public void onDismiss(DialogInterface dialog)
            {
                isPaused = false;
            }
        });

        alertDialog = builder.create();
    }
}