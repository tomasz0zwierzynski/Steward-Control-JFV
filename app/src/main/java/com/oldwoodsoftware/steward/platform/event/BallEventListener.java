package com.oldwoodsoftware.steward.platform.event;

/**
 * Created by Nails on 28.09.2017.
 */

public interface BallEventListener {
    void onBallPositionChanged(float x, float y);
    void onBallDetectionChanged(boolean isDetected);
}
