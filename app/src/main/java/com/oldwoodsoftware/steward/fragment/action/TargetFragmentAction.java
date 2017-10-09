package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.core.calculation.UnitConverter;
import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandExecutor;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.core.command.concretion.MoveTo;
import com.oldwoodsoftware.steward.core.command.concretion.StartMode;
import com.oldwoodsoftware.steward.core.command.concretion.Submit;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetMode;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.core.request.AbstractRequest;
import com.oldwoodsoftware.steward.core.request.type.RequestType;
import com.oldwoodsoftware.steward.fragment.agent.TargetFragmentAgent;
import com.oldwoodsoftware.steward.fragment.base.TargetFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.component.BallOnPlate;
import com.oldwoodsoftware.steward.platform.component.PidControlCase;
import com.oldwoodsoftware.steward.platform.component.StateMachine;
import com.oldwoodsoftware.steward.platform.event.BallEventListener;
import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

public class TargetFragmentAction extends FragmentAction implements TargetFragmentAgent, BallEventListener, PidControlEventListener {
    TargetFragment own;
    BallOnPlate ballOnPlate;
    PidControlCase pidControlX;
    PidControlCase pidControlY;
    UnitConverter unitConverter;

    CommandParser cmdParser;

    //Temporarly
    public TargetFragmentAction(TargetFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        ballOnPlate = pContext.getBallOnPlate();
        pidControlX = pContext.getPidControlX();
        pidControlY = pContext.getPidControlY();
        unitConverter = pContext.getProcessContext().getUnitConverter();
        cmdParser = pContext.getProcessContext().getCommandParser();
        isActive = true;
    }

    @Override
    public void outTargetPositionChanged(float x_per, float y_per) {
        System.out.println("Debug: onNewTargetPosition() called!");
        float[] XY = unitConverter.platePercentToFloat(x_per,y_per);
            System.out.println("Debug: TargetValuesToSend: X = " + String.valueOf(XY[0]) + " Y = " + String.valueOf(XY[1]));
        //Intentionally axes reversed.
        cmdParser.addRequest(AbstractRequest.createRequest(RequestType.setTargetX,XY[0]));
        cmdParser.addRequest(AbstractRequest.createRequest(RequestType.setTargetY,XY[1]));
        cmdParser.pushRequests();
    }

    @Override
    public float inGetPanelLengthRatio() {
        return unitConverter.plateGetRatio();
    }

    @Override
    public void onBallPositionChanged(float x, float y) {
        //TODO: implement method
    }

    @Override
    public void onBallDetectionChanged(boolean isDetected) {
        //TODO: implement method
    }

    @Override
    public void onPidSetpointChanged(int id, float setpoint) {

    }
}
