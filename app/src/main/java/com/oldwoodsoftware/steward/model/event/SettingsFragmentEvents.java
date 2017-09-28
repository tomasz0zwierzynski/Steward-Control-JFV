package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.model.bluetooth.CmdProtocol;
import com.oldwoodsoftware.steward.model.responsibility.listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.SettingsFragment;

public class SettingsFragmentEvents extends FragmentEvents implements SettingsFragmentListener{
    SettingsFragment own;

    public SettingsFragmentEvents(SettingsFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
    }

    @Override
    public void onBluetoothConnectionButtonChecked() {
        //pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Connecting);
        if (pContext.getBtConnection().isInitialized() == false) {
            try {
                pContext.getBtConnection().init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

            try {
                pContext.getBtConnection().connect();
                CmdProtocol cmdProtocol = new CmdProtocol(pContext.getBtConnection(), pContext);
                pContext.getBtConnection().addBluetoothListener(cmdProtocol);
                pContext.setCmdProtocol(cmdProtocol);

            } catch (Exception e) {
                //pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
            }

            try {
                if (pContext.getBtConnection().isConnected()) {
                    pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Connected);
                }
            } catch (Exception e) {
                //statusBar.updateBluetoothStatus(e.getMessage());
            }


    }

    @Override
    public void onBluetoothConnectionButtonUnchecked() {

        //pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.TurningOff);

        try {
            pContext.getBtConnection().disconnect();
        } catch (Exception e) {
            //pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
        }

        pContext.setCmdProtocol(null);
        //pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Disconnected);
    }

    @Override
    public boolean isBluetoothConnected() {
        if (pContext.getBtConnection().isConnected() == false){
            return false;
        }else{
            return true;
        }
    }
}
