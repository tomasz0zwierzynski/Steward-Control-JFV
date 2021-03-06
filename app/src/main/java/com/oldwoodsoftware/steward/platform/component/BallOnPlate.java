package com.oldwoodsoftware.steward.platform.component;

import android.util.Log;

import com.oldwoodsoftware.steward.platform.event.BallEventListener;

import java.util.ArrayList;
import java.util.List;

public class BallOnPlate {
    private float posX = 0;
    private float posY = 0;
    private boolean isDetected = false;
    private List<BallEventListener>ballEventListeners = new ArrayList<>();

    public void setPosX(float posX) {
        this.posX = posX;
        emitPositionChanged();
    }

    public void setPosY(float posY) {
        this.posY = posY;
        emitPositionChanged();
    }

    public void setDetected(boolean detected) {
        isDetected = detected;
        emitDetectionChanged();
    }


    public float getPosX() {
        return posX;
    }


    public float getPosY() {
        return posY;
    }


    public boolean isDetected() {
        return isDetected;
    }

    public void addBallEventListener(BallEventListener bel){
        ballEventListeners.add(bel);
    }

    private void emitDetectionChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".detectionChanged emitting...");
        for (BallEventListener bel : ballEventListeners){
            bel.onBallDetectionChanged(isDetected);
        }
    }

    private void emitPositionChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".positionChanged emitting...");
        for (BallEventListener bel : ballEventListeners){
            bel.onBallPositionChanged(posX, posY);
        }
    }

    @Override
    public String toString(){
        return "platform.component." + this.getClass().getSimpleName() + ", registered listeners " + ballEventListeners.toString();
    }
}
