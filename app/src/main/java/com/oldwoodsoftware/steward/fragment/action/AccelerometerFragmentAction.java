package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.fragment.agent.AccelerometerFragmentAgent;
import com.oldwoodsoftware.steward.old_model.responsibility.listener.AccelerometerHandlerListener;
import com.oldwoodsoftware.steward.fragment.base.AccelerometerFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class AccelerometerFragmentAction extends FragmentAction implements AccelerometerFragmentAgent, AccelerometerHandlerListener{
    AccelerometerFragment own;

    public AccelerometerFragmentAction(AccelerometerFragment fragment){
        own = fragment;
     //   pContext.getAccHandler().addSensorChangedListener(this);
    }

    @Override
    public void activate(PlatformContext pContext){
        isActive = true;
    }

    @Override
    public void onAccelerometerFragmentStateChange(boolean state) {
        if (state) { //active
            //pContext.getAccHandler().startRead();
        }else{
            //pContext.getAccHandler().stopRead();
        }
    }

    @Override
    public void onAccelerometerHandlerNewData(float pitch, float roll) {
        //TODO; btSending
        try {
            //pContext.getCmdProtocol().putAccelerometerCommand(pitch,roll);
        } catch (Exception e) { }
        try {
            own.updateControls(pitch,roll);
        }catch(IllegalStateException ex){ }
    }
}
