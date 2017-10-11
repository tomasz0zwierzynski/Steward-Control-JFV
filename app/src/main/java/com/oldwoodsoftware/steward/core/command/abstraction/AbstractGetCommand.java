package com.oldwoodsoftware.steward.core.command.abstraction;

import com.oldwoodsoftware.steward.platform.PlatformContext;

public abstract class AbstractGetCommand extends AbstractCommand {
    public AbstractGetCommand(PlatformContext pContext,boolean incoming) {
        super(pContext,incoming);
    }
}
