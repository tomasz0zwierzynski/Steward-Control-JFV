package com.slinkwoodsoftware.steward.sensor;

public interface AccelerometerHandlerListener {
    void onAccelerometerHandlerNewData(float pitch, float roll);
}
