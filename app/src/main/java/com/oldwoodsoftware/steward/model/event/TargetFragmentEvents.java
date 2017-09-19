package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.TargetFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.TargetFragment;

public class TargetFragmentEvents extends FragmentEvents implements TargetFragmentListener {
    TargetFragment own;

    public TargetFragmentEvents(TargetFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
    }

    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
        pContext.getPG().calculateRealTargetXY(x_per,y_per);
        float x = pContext.getPG().getTargetX();
        float y = pContext.getPG().getTargetY();

        try {
            pContext.getCmdProtocol().putTargetCommand(x,y);
        } catch (Exception e) { }

    }

    @Override
    public void setCurrentBallPosition(float x_per, float y_per, boolean show) {
        pContext.getPG().setBallXY(x_per,y_per);

        if(show){
            float[] per_XY = pContext.getPG().getPercentBallXY();
            try {
                own.onCurrentBallPositionChanged(per_XY[0],per_XY[1],true);
            }catch(IllegalStateException ex){ }
        }else{
            try {
                own.onCurrentBallPositionChanged(0,0,false);
            }catch(IllegalStateException ex){ }
        }
    }

    @Override
    public float getPanelLenghtRatio() {
        return pContext.getPG().getRatio();
    }
}
