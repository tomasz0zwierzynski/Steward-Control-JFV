package com.oldwoodsoftware.steward.model;

import android.content.Context;

import com.oldwoodsoftware.steward.MainActivity;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.bluetooth.CmdProtocol;
import com.oldwoodsoftware.steward.model.event.FragmentEventManager;
import com.oldwoodsoftware.steward.model.sensor.AccelerometerHandler;

public class PlatformContext {

    private BluetoothConnection btConnection;
    private CmdProtocol cmdProtocol;

    private InverseKinematics ik;
    private PanelGeometrics pg;

    private StatusBarUpdater statusBar;
    private AccelerometerHandler accHandler;

    private FragmentEventManager fragmentEventManager;

    private MainActivity parentActivity;

    public PlatformContext(MainActivity activity){
        parentActivity = activity;

        //Creating StatusBarUpdater
        statusBar = new StatusBarUpdater(activity);
        //Creating Accelerometer handler
        accHandler = new AccelerometerHandler(activity);
        //IK paramters
        ik = new InverseKinematics(activity.getBaseContext(), new float[] {-20,-20,-20,-12,-12,-12}, new float[] {+20,+20,+20,+12,+12,+12} );
        //Geometrical parameters
        pg = new PanelGeometrics(activity.getBaseContext(), 150f, 100f); // default: 297.0f, 210.0f

        fragmentEventManager = parentActivity.getFragmentEventManager();
    }

    public void setFragmentEventManager(FragmentEventManager fem){
        fragmentEventManager = fem;
    }

    public FragmentEventManager getFragmentEventManager(){
        return fragmentEventManager;
    }

    public void setBtConnection(BluetoothConnection btConnection) {
        this.btConnection = btConnection;
    }

    public void setCmdProtocol(CmdProtocol cmdProtocol) {
        this.cmdProtocol = cmdProtocol;
    }

    public MainActivity getParentActivity() {
        return parentActivity;
    }

    public CmdProtocol getCmdProtocol() {
        return cmdProtocol;
    }

    public BluetoothConnection getBtConnection() {
        return btConnection;
    }

    public AccelerometerHandler getAccHandler() {
        return accHandler;
    }

    public StatusBarUpdater getStatusBar() {
        return statusBar;
    }

    public PanelGeometrics getPG() {
        return pg;
    }

    public InverseKinematics getIK() {
        return ik;
    }

}
