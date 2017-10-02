package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.core.calculation.UnitConverter;
import com.oldwoodsoftware.steward.fragment.base_listener.TargetFragmentListener;
import com.oldwoodsoftware.steward.fragment.base.TargetFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.component.BallOnPlate;
import com.oldwoodsoftware.steward.platform.component.PidControlCase;
import com.oldwoodsoftware.steward.platform.event.BallEventListener;
import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;

public class TargetFragmentAction extends FragmentAction implements TargetFragmentListener, BallEventListener, PidControlEventListener {
    TargetFragment own;
    BallOnPlate ballOnPlate;
    PidControlCase pidControlX;
    PidControlCase pidControlY;
    UnitConverter unitConverter;

    public TargetFragmentAction(TargetFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        ballOnPlate = pContext.getBallOnPlate();
        pidControlX = pContext.getPidControlX();
        pidControlY = pContext.getPidControlY();
        unitConverter = pContext.getProcessContext().getUnitConverter();
        isActive = true;
    }

    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
      //  pContext.getPG().calculateRealTargetXY(x_per,y_per);
      //  float x = pContext.getPG().getTargetX();
      //  float y = pContext.getPG().getTargetY();

        try {
      //      pContext.getCmdProtocol().putTargetCommand(x,y);
        } catch (Exception e) { }

    }

    @Override
    public void setCurrentBallPosition(float x_per, float y_per, boolean show) {
      //  pContext.getPG().setBallXY(x_per,y_per);

        if(show){
      //      float[] per_XY = pContext.getPG().getPercentBallXY();
            try {
     //           own.onCurrentBallPositionChanged(per_XY[0],per_XY[1],true);
            }catch(IllegalStateException ex){ }
        }else{
            try {
      //          own.onCurrentBallPositionChanged(0,0,false);
            }catch(IllegalStateException ex){ }
        }
    }

    @Override
    public float getPanelLenghtRatio() {
        return 0f;//pContext.getPG().getRatio();
    }

    @Override
    public void onBallPositionChanged(float x, float y) {

    }

    @Override
    public void onBallDetectionChanged(boolean isDetected) {

    }

    @Override
    public void onPidSetpointChanged(int id, float setpoint) {

    }
}
