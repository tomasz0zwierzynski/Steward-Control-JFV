package com.oldwoodsoftware.steward.fragment.agent;

public interface SettingsFragmentAgent {
    void onBluetoothConnectionButtonChecked();
    void onBluetoothConnectionButtonUnchecked();
    boolean isBluetoothConnected();
}
