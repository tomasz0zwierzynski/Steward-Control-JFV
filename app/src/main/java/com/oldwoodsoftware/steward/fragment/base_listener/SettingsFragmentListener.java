package com.oldwoodsoftware.steward.fragment.base_listener;

public interface SettingsFragmentListener {
    void onBluetoothConnectionButtonChecked();
    void onBluetoothConnectionButtonUnchecked();
    boolean isBluetoothConnected();
}
