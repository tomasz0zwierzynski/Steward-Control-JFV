package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.platform.component.StateMachine;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;
import com.oldwoodsoftware.steward.platform.event.StateMachineEventListener;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

public class CommandParser implements StateMachineEventListener, BluetoothEventListener{
    private StateMachine stateMachine;
    private CommandCreator cmdCreate;
    private CommandOutputQueuer outQueuer;

    public CommandParser(StateMachine stateMachine, CommandCreator cmdCreate, CommandOutputQueuer outQueuer){
        this.stateMachine = stateMachine;
        this.cmdCreate = cmdCreate;
        this.outQueuer = outQueuer;
    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {

    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {

    }

    @Override
    public void onBluetoothMessage(String msg) {

    }

    @Override
    public void onStateMachineModeChanged(PlatformMode mode) {

    }
}
