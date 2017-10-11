package com.oldwoodsoftware.steward.core.command.concretion;

import android.util.Log;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class Empty extends AbstractCommand {

    public Empty(PlatformContext pContext,boolean incoming) {
        super(pContext,incoming);
    }

    @Override
    public void execute() throws Exception {
        logExecution();
    }
}
