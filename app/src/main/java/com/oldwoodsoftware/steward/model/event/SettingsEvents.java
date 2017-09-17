package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.model.bluetooth.CmdProtocol;
import com.oldwoodsoftware.steward.model.responsibility.listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.SettingsFragment;

public class SettingsEvents extends FragmentEvent implements SettingsFragmentListener{

    SettingsFragment own;
    PlatformContext pContext;

    public SettingsEvents(SettingsFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
    }

    @Override
    public void onBluetoothConnectionButtonChecked() {
        pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Connecting);

        try {
            BluetoothConnection btConnection = new BluetoothConnection(pContext.getParentActivity());
            CmdProtocol cmdProtocol = new CmdProtocol(btConnection,pContext);
            btConnection.addBluetoothListener(cmdProtocol);
            pContext.setBtConnection(btConnection);
            pContext.setCmdProtocol(cmdProtocol);

        } catch (Exception e) {
            pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
        }

        try {
            if (pContext.getBtConnection().isConnected()) {
                pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Connected);
            }
        }catch (Exception e){
            //statusBar.updateBluetoothStatus(e.getMessage());
        }
    }

    @Override
    public void onBluetoothConnectionButtonUnchecked() {

        pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.TurningOff);

        try {
            pContext.getBtConnection().disconnect();
        } catch (Exception e) {
            pContext.getStatusBar().updateBluetoothStatus(e.getMessage());
        }

        pContext.setBtConnection(null);
        pContext.setCmdProtocol(null);
        pContext.getStatusBar().updateBluetoothStatus(BluetoothStatus.Disconnected);
    }

    @Override
    public boolean isBluetoothConnected() {
        if (pContext.getBtConnection() == null){
            return false;
        }else{
            return true;
        }
    }
}
