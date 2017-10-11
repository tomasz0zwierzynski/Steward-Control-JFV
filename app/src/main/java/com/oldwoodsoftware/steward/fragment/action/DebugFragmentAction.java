package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.fragment.agent.DebugFragmentAgent;
import com.oldwoodsoftware.steward.fragment.base.DebugFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;

public class DebugFragmentAction extends FragmentAction implements DebugFragmentAgent, BluetoothEventListener {
    DebugFragment own;
    //TODO: CommandParser and in another Action Classes
    public DebugFragmentAction(DebugFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        isActive = true;
    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {

    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {
        //TODO: Print bt data
    }

    @Override
    public void onBluetoothMessage(String msg) {
        //TODO: Print bt msgs
    }

    @Override
    public void outDebugCommandPushed(String cmd) {
        //TODO: Send command
    }
}
