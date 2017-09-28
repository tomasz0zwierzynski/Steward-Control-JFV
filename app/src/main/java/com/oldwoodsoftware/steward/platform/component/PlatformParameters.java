package com.oldwoodsoftware.steward.platform.component;

import java.util.Arrays;

/**
 * Created by Nails on 28.09.2017.
 */

public class PlatformParameters {
    public float usableWidth;
    public float usableHeight;
    public float[] minXYZABCRange;
    public float[] maxXYZABCRange;

    public PlatformParameters(float usableWidth, float usableHeight, float[] minXYZABCRange, float[] maxXYZABCRange) throws IllegalArgumentException{
        if ((minXYZABCRange.length != 6) || (maxXYZABCRange.length != 6)){
            throw new IllegalArgumentException("Ranges has to be deliver in float[6] arrays.");
        }

        this.usableWidth = usableWidth;
        this.usableHeight = usableHeight;
        this.minXYZABCRange = Arrays.copyOf(minXYZABCRange,minXYZABCRange.length);
        this.maxXYZABCRange =  Arrays.copyOf(maxXYZABCRange,maxXYZABCRange.length);
    }
}
