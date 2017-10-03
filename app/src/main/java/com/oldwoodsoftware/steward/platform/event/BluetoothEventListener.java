package com.oldwoodsoftware.steward.platform.event;

import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;

/**
 * Created by Nails on 28.09.2017.
 */

public interface BluetoothEventListener {
    void onBluetoothConnectionStateChanged(BluetoothState btState);
    void onBluetoothDataReceived(byte[] data);
    void onBluetoothMessage(String msg);
}
