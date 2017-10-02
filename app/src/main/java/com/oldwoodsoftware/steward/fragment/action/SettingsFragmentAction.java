package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.old_model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.old_model.responsibility.listener.BluetoothDataListener;
import com.oldwoodsoftware.steward.fragment.base_listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.fragment.base.SettingsFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;

public class SettingsFragmentAction extends FragmentAction implements SettingsFragmentListener, BluetoothDataListener{
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
    public void onBluetoothConnectionButtonChecked() {
        if (btConnection.isInitialized() == false) {
            try {
                btConnection.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

            try {
                btConnection.connect();
            } catch (Exception e) {
                //pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
            }
    }

    @Override
    public void onBluetoothConnectionButtonUnchecked() {
/*
        //pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.TurningOff);

        try {
            pContext.getBtConnection().disconnect();
        } catch (Exception e) {
            //pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
        }

        pContext.setCmdProtocol(null);
        //pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Disconnected);
    */

    }

    @Override
    public boolean isBluetoothConnected() {
      /*  if (pContext.getBtConnection().isConnected() == false){
            return false;
        }else{
            return true;
        }*/
        return false;
    }

    @Override
    public void onBluetoothData(byte[] data) {

    }

    @Override
    public void onBluetoothStateChanged(BluetoothStatus btStat) {

    }

    @Override
    public void onBluetoothMessage(String msg) {

    }
}
