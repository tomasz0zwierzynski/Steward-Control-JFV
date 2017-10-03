package com.oldwoodsoftware.steward.core.bluetooth;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandInputQueuer;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;

import java.nio.charset.StandardCharsets;

public class BluetoothReceiver implements BluetoothEventListener{
    private CommandCreator cmdCreate;
    private CommandInputQueuer inQueuer;

    public BluetoothReceiver(CommandCreator cmdCreate, CommandInputQueuer inQueuer){
        this.cmdCreate = cmdCreate;
        this.inQueuer = inQueuer;
    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {

    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {
        String sBytes = new String(data, StandardCharsets.UTF_8);
        inQueuer.addInputCommandToQueue(cmdCreate.createCommand(sBytes,true));
    }

    @Override
    public void onBluetoothMessage(String msg) {

    }
}
