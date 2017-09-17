package com.oldwoodsoftware.steward.model.event;

import android.app.Activity;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerFragmentStateListener;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerHandlerListener;
import com.oldwoodsoftware.steward.view.fragment.AccelerometerFragment;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;

public class AccelerometerEvents extends FragmentEvent implements AccelerometerFragmentStateListener, AccelerometerHandlerListener{

    AccelerometerFragment own;
    PlatformContext pContext;

    public AccelerometerEvents(AccelerometerFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
        pContext.getAccHandler().addSensorChangedListener(this);
    }

    @Override
    public void onAccelerometerFragmentStateChange(boolean state) {
        if (state) { //active
            pContext.getAccHandler().startRead();
        }else{
            pContext.getAccHandler().stopRead();
        }
    }

    @Override
    public void onAccelerometerHandlerNewData(float pitch, float roll) {
        //TODO; btSending
        try {
            pContext.getCmdProtocol().putAccelerometerCommand(pitch,roll);
        } catch (Exception e) { }
        try {
            own.updateControls(pitch,roll);
        }catch(IllegalStateException ex){ }
    }
}
