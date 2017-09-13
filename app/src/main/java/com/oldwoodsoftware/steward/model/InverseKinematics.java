package com.oldwoodsoftware.steward.model;

import android.content.Context;
import android.content.res.Resources;

import com.oldwoodsoftware.steward.R;

public class InverseKinematics {

    private Context context;

    private float[] minXYZABCranges = new float[6]; //0-X 1-Y 2-Z 3-A 4-B 5-C
    private float[] maxXYZABCranges = new float[6];

    private float[] currentXYZABCvalues = new float[6];

    //TODO: Control the 6 axes in arrays
    public InverseKinematics(Context _context, float[] _minXYZABCranges, float[] _maxXYZABCranges){
        minXYZABCranges = _minXYZABCranges;
        maxXYZABCranges = _maxXYZABCranges;
        context = _context;
    }

    public void calculateCurrentXYZABCvalues(int[] sliderProgresses){
        for (int i=0; i<sliderProgresses.length;i++){
            currentXYZABCvalues[i] = (float) (0.001 * (maxXYZABCranges[i]-minXYZABCranges[i])*sliderProgresses[i] + minXYZABCranges[i]);
        }
    }

    public float[] getCurrentXYZABCvalues(){

        return currentXYZABCvalues;
    }

    public String[] getStringRepresentation(){
        String[] strings = new String[6];
        strings[0] = context.getString(R.string.inverse_TV_X) + String.valueOf(currentXYZABCvalues[0]) + " [mm]";
        strings[1] = context.getString(R.string.inverse_TV_Y) + String.valueOf(currentXYZABCvalues[1]) + " [mm]";
        strings[2] = context.getString(R.string.inverse_TV_Z) + String.valueOf(currentXYZABCvalues[2]) + " [mm]";
        strings[3] = context.getString(R.string.inverse_TV_A) + String.valueOf(currentXYZABCvalues[3]) + " [deg]";
        strings[4] = context.getString(R.string.inverse_TV_B) + String.valueOf(currentXYZABCvalues[4]) + " [deg]";
        strings[5] = context.getString(R.string.inverse_TV_C) + String.valueOf(currentXYZABCvalues[5]) + " [deg]";

        return strings;
    }

}
