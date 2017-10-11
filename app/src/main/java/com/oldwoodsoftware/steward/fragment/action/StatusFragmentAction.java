package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.fragment.base.StatusFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.event.BallEventListener;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;
import com.oldwoodsoftware.steward.platform.event.PlateEventListener;
import com.oldwoodsoftware.steward.platform.event.SystemEventListener;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;
import com.oldwoodsoftware.steward.platform.type.Configuration;

public class StatusFragmentAction extends FragmentAction implements SystemEventListener, BallEventListener, BluetoothEventListener, PlateEventListener {
    StatusFragment own;

    public StatusFragmentAction(StatusFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        isActive = true;
    }

    @Override
    public void onSystemCpuUsageChanged(float cpuUsage) {
        own.updateCPUusage(cpuUsage);
        //this.cpuUsage.setText(" " + String.valueOf(value) + " %");
    }

    @Override
    public void onSystemFreeHeapChanged(int freeHeap) {
        own.updateFreeHeap(freeHeap);
        //this.freeHeap.setText(" " + String.valueOf(value) + " bytes");
    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {
        own.updateBluetoothStatus(btState);
    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {

    }

    @Override
    public void onBluetoothMessage(String msg) {
       own.updateBluetoothMessage(msg);
    }

    @Override
    public void onPlateConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onBallPositionChanged(float x, float y) {

    }

    @Override
    public void onBallDetectionChanged(boolean isDetected) {

    }
}
