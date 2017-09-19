package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerFragmentStateListener;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerHandlerListener;
import com.oldwoodsoftware.steward.view.fragment.AccelerometerFragment;

public class AccelerometerFragmentEvents extends FragmentEvents implements AccelerometerFragmentStateListener, AccelerometerHandlerListener{
    AccelerometerFragment own;

    public AccelerometerFragmentEvents(AccelerometerFragment fragment, PlatformContext context){
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
