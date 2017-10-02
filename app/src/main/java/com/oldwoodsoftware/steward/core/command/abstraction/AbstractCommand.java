package com.oldwoodsoftware.steward.core.command.abstraction;

import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;

public abstract class AbstractCommand {
    protected float value;
    protected BluetoothConnection btConnection;
    public abstract void execute() throws Exception;
}
