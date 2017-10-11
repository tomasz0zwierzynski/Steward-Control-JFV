package com.oldwoodsoftware.steward.core.calculation;

import com.oldwoodsoftware.steward.platform.component.PlatformParameters;

public class UnitConverter {
    private PlatformParameters params;

    public UnitConverter(PlatformParameters params){
        this.params = params;
    }

    public float plateGetRatio(){
        return params.usableHeight / params.usableWidth;
    }

    public float[] platePercentToFloat(float x_percent, float y_percent){
        float[] values = new float[2];
        values[0] = ((x_percent/100) * params.usableWidth) - params.usableWidth/2;
        values[1] = ((y_percent/100) * params.usableHeight) - params.usableHeight/2;

        return values;
    }

    public float[] plateFloatToPercent(float x_real, float y_real){
        float[] values = new float[2];
        values[0] = (float) ((100/params.usableWidth)*(x_real+0.5*params.usableWidth));
        values[1] = (float) ((100/params.usableHeight)*(y_real+0.5*params.usableHeight));
        return values;
    }

    public float inversePromilesToFloat(int index, int value){
        return ((float) (0.001 * (params.maxXYZABCRange[index]-params.minXYZABCRange[index])*value + params.minXYZABCRange[index]));
    }

    public int inverseFloatToPromiles(int index, float value){
        return ((int) (1000 * ((value-params.minXYZABCRange[index])/(params.maxXYZABCRange[index]-params.minXYZABCRange[index]))));
    }

}
