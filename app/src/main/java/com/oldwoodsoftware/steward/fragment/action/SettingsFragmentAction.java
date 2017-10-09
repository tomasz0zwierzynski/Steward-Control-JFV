package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;
import com.oldwoodsoftware.steward.fragment.agent.SettingsFragmentAgent;
import com.oldwoodsoftware.steward.fragment.base.SettingsFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;

public class SettingsFragmentAction extends FragmentAction implements SettingsFragmentAgent, BluetoothEventListener {
    SettingsFragment own;

    private BluetoothConnection btConnection;
    private boolean isBtConnected = false;

    public SettingsFragmentAction(SettingsFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext platformContext){
        btConnection = platformContext.getBluetoothConnection();
        isBtConnected = btConnection.isConnected();
        isActive = true;
    }

    @Override
    public void outBluetoothButtonON() {
        if (btConnection.isInitialized() == false) {
            try {
                btConnection.init();
            } catch (Exception e) { }
        }

            try {
                btConnection.connect();
            } catch (Exception e) { }
    }

    @Override
    public void outBluetoothButtonOFF() {
        try {
            btConnection.disconnect();
        } catch (Exception e) {

        }
    }

    @Override
    public boolean inIsConnected() {
        return btConnection.isConnected();
    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {
        //TODO: Synchronize button with actual connection (in toggleElement could unregister listener for for call)
        /*if(btState == BluetoothState.connected){
            own.forceBtButtonState(true);
        }else{
            own.forceBtButtonState(false);
        }*/
    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {

    }

    @Override
    public void onBluetoothMessage(String msg) {

    }
}
