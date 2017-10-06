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
    PlatformContext pContext;

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
        //Temporarly
        this.pContext = pContext;
        isActive = true;
    }

    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
        System.out.println("Debug: onNewTargetPosition() called!");
        float[] XY = unitConverter.platePercentToFloat(x_per,y_per);
            System.out.println("Debug: TargetValuesToSend: X = " + String.valueOf(XY[0]) + " Y = " + String.valueOf(XY[1]));
        /*cmdParser.addRequest(AbstractRequest.createRequest(RequestType.setTargetX,XY[0]));
        cmdParser.addRequest(AbstractRequest.createRequest(RequestType.setTargetY,XY[1]));
        cmdParser.pushRequests();*/

        StateMachine stateMachine = pContext.getStateMachine();
        CommandCreator cmdCreate = pContext.getProcessContext().getCommandFactory();
        CommandExecutor cmdExecute = pContext.getProcessContext().getCommandExecutor();

        if( stateMachine.getMode() != PlatformMode.pidMode){
            try {
                //cmdCreate.createCommand(CommandType.setMode,(float)PlatformMode.pidMode.get_uC_mode()).execute();
                //(new SetMode(pContext, PlatformMode.pidMode)).execute();
                String msg = CommandType.setMode.get_uC_command_code_as_string() + "=" + PlatformMode.pidMode.get_uC_mode_as_string();
                pContext.getBluetoothConnection().sendMessage(msg.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stateMachine.setMode(PlatformMode.pidMode);
        }
        if( stateMachine.isModeStarted() == false){
            try {
                //cmdCreate.createCommand(CommandType.startMode,0).execute();
                (new StartMode(pContext,false)).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stateMachine.setModeStarted(true);
        }
        if( stateMachine.isMoveTo() == false){
            try {
                //cmdCreate.createCommand(CommandType.moveTo,0).execute();
                (new MoveTo(pContext,false)).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stateMachine.setMoveTo(true);
        }
        try {
            cmdCreate.createCommand(CommandType.setSetpointX, XY[0],false).execute();
            cmdCreate.createCommand(CommandType.setSetpointY, XY[1],false).execute();
            //cmdCreate.createCommand(CommandType.submit, 0).execute();

            (new Submit(pContext,false)).execute();
            }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /*
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
    */

    @Override
    public float getPanelLenghtRatio() {
        return unitConverter.plateGetRatio();
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
