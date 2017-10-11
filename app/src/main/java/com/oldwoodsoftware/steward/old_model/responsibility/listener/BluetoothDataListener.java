package com.oldwoodsoftware.steward.old_model.responsibility.listener;

import com.oldwoodsoftware.steward.old_model.bluetooth.BluetoothStatus;

public interface BluetoothDataListener {
    void onBluetoothData(final byte[] data);
    void onBluetoothStateChanged(BluetoothStatus btStat);
    void onBluetoothMessage(String msg);
}
