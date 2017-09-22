package com.oldwoodsoftware.steward.model.responsibility.listener;

import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;

public interface BluetoothDataListener {
    void onBluetoothData(final byte[] data);
    void onBluetoothStateChanged(BluetoothStatus btStat);
    void onBluetoothMessage(String msg);
}
