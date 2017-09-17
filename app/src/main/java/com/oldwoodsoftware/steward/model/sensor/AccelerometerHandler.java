package com.oldwoodsoftware.steward.model.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerHandlerListener;

import java.util.ArrayList;
import java.util.List;

public class AccelerometerHandler implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private Activity parentActivity;
    private List<AccelerometerHandlerListener> handlerListeners = new ArrayList<AccelerometerHandlerListener>();

    private float pitchGain = 0.3f;
    private float rollGain = 0.3f;
    private float pitchOut;
    private float rollOut;

    public AccelerometerHandler(Activity ma){
        parentActivity = ma;

        //To remove
        //handlerListeners.add( (AccelerometerHandlerListener) parentActivity );

        senSensorManager = (SensorManager) ma.getSystemService(ma.getApplicationContext().SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        calculateOutput(event);
        for (AccelerometerHandlerListener ahl : handlerListeners){
            ahl.onAccelerometerHandlerNewData(pitchOut,rollOut);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startRead(){
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopRead(){
        senSensorManager.unregisterListener(this);
    }

    public void onResume(){

    }

    public void onPause(){
        senSensorManager.unregisterListener(this);
    }

    private void calculateOutput(SensorEvent e){
        float x = e.values[0];
        float y = e.values[1];
        float z = e.values[2];

        //old equations that seemed to work, but honestly I don't believe that
        float roll = (float) (Math.atan(x / Math.sqrt(Math.pow(z, 2) + Math.pow(y, 2))));
        float pitch = (float) (Math.atan(y / Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2))));

        roll *= 180.0 / Math.PI;
        pitch *= 180.0 / Math.PI;

        //some equations found on the Internet
/*
        float roll = (float) (Math.atan2(y, z) * 180/Math.PI);
        float pitch = (float) (Math.atan2(-x, Math.sqrt(y*y + z*z)) * 180/Math.PI);
*/
        rollOut = roll * rollGain;
        pitchOut = pitch * pitchGain;
    }

    public void addSensorChangedListener(AccelerometerHandlerListener listener){
        handlerListeners.add(listener);
    }
}
