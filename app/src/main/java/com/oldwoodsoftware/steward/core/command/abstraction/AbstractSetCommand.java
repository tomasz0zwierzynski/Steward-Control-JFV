package com.oldwoodsoftware.steward.core.command.abstraction;

import com.oldwoodsoftware.steward.platform.PlatformContext;

public abstract class AbstractSetCommand extends AbstractCommand {
    public AbstractSetCommand(PlatformContext pContext,boolean incoming) {
        super(pContext,incoming);
    }
}
