package com.oldwoodsoftware.steward.core.command.abstraction;

import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public abstract class AbstractCommand {
    protected float value = 0;
    protected PlatformContext pContext;
    protected BluetoothConnection btConnection;
    protected boolean incoming = false;

    public abstract void execute() throws Exception;

    public AbstractCommand(PlatformContext pContext,boolean incoming){
        this.pContext = pContext;
        this.btConnection = pContext.getBluetoothConnection();
        this.incoming = incoming;
    }
}
