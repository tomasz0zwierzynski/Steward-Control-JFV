package com.oldwoodsoftware.steward.platform.component;

import android.util.Log;

import com.oldwoodsoftware.steward.platform.event.StateMachineEventListener;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

import java.util.ArrayList;
import java.util.List;

//TODO: remember more states, like isMoveTo...

public class StateMachine {
    private List<StateMachineEventListener> stateMachineEventListeners = new ArrayList<>();

    private PlatformMode mode = PlatformMode.none;
    private boolean isModeStarted = false;
    private boolean isMoveTo = false;

    public void setMoveTo(boolean isMoveTo){
        this.isMoveTo = isMoveTo;
        emitMoveToChanged();
    }

    public void setModeStarted(boolean isModeStarted){
        this.isModeStarted = isModeStarted;
        emitModeStartedChanged();
    }

    public void setMode(PlatformMode mode) {
        this.mode = mode;
        emitModeChanged();
    }

    public PlatformMode getMode() {
        return mode;
    }

    public boolean isModeStarted(){
        return isModeStarted;
    }

    public boolean isMoveTo(){
        return isMoveTo;
    }

    public void reset(){
        setMode(PlatformMode.none);
        setModeStarted(false);
        setMoveTo(false);
    }

    public StateMachine getCopy(){
        StateMachine sm = new StateMachine();
        sm.mode = this.mode;
        sm.isModeStarted = this.isModeStarted;
        sm.isMoveTo = this.isMoveTo;
        return sm;
    }

    public void setFromTemplate(StateMachine sm){
        setMode(sm.getMode());
        setModeStarted(sm.isModeStarted());
        setMoveTo(sm.isMoveTo());
    }

    public void addStateMachineEventListener(StateMachineEventListener sml){
        stateMachineEventListeners.add(sml);
    }

    private void emitModeChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".modeChanged emitting...");
        for (StateMachineEventListener sml : stateMachineEventListeners){
            sml.onStateMachineModeChanged(mode);
        }
    }

    private void emitMoveToChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".moveToChanged emitting...");
        for (StateMachineEventListener sml : stateMachineEventListeners){
            sml.onMoveToChanged(isMoveTo);
        }
    }

    private void emitModeStartedChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".modeStartedChanged emitting...");
        for (StateMachineEventListener sml : stateMachineEventListeners){
            sml.onModeStartedChanged(isModeStarted);
        }
    }

    @Override
    public String toString(){
        return "platform.component." + this.getClass().getSimpleName() + ", registered listeners " + stateMachineEventListeners.toString();
    }
}
