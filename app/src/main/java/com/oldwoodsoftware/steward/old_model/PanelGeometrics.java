package com.oldwoodsoftware.steward.old_model;

import android.content.Context;

public class PanelGeometrics {
    private final float usable_width;
    private final float usable_height;

    private float length_ratio;

    private float target_x = 0; //real value
    private float target_y = 0;

    private float ball_x = 0; //real value
    private float ball_y = 0;

    public PanelGeometrics(Context _context, float _width_in_mm, float _height_in_mm){
        usable_height = _height_in_mm;
        usable_width = _width_in_mm;
        length_ratio = usable_height/usable_width;
    }

    public float getRatio(){
        return length_ratio;
    }

    public float[] getPercentBallXY(){
        float[] values = new float[2];
        values[0] = (float) ((100/usable_width)*(ball_x+0.5*usable_width));
        values[1] = (float) ((100/usable_height)*(ball_y+0.5*usable_height));

        return values;
    }

    public void calculateRealTargetXY(float x_per, float y_per){
        //Depending on panel XY frame, calculate values
        target_x = ((x_per/100) * usable_width) - usable_width/2;
        target_y = ((y_per/100) * usable_height) - usable_height/2;
    }

    public void setBallXY(float x, float y){
        ball_x = x;
        ball_y = y;
    }

    public float getTargetX(){
        return target_x;
    }

    public float getTargetY(){
        return target_y;
    }

}
