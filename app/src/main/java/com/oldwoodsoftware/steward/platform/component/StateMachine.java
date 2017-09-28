package com.oldwoodsoftware.steward.platform.component;

import com.oldwoodsoftware.steward.platform.event.StateMachineEventListener;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nails on 28.09.2017.
 */

public class StateMachine {
    private PlatformMode mode = PlatformMode.none;
    private List<StateMachineEventListener> stateMachineEventListeners = new ArrayList<>();

    public void setMode(PlatformMode mode) {
        this.mode = mode;
        emitModeChanged();
    }

    public PlatformMode getMode() {
        return mode;
    }

    public void addStateMachineEventListener(StateMachineEventListener sml){
        stateMachineEventListeners.add(sml);
    }

    private void emitModeChanged(){
        for (StateMachineEventListener sml : stateMachineEventListeners){
            sml.onStateMachineModeChanged(mode);
        }
    }
}
