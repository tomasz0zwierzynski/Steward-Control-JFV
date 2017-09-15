package com.oldwoodsoftware.steward.model.responsibility.listener;

public interface SettingsFragmentListener {
    void onBluetoothConnectionButtonChecked();
    void onBluetoothConnectionButtonUnchecked();
    boolean isBluetoothConnected();
}
