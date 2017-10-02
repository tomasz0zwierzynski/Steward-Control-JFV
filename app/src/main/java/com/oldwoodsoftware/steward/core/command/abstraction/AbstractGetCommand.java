package com.oldwoodsoftware.steward.core.command.abstraction;

import com.oldwoodsoftware.steward.platform.PlatformContext;

public abstract class AbstractGetCommand extends AbstractCommand {
    protected PlatformContext pContext;
    protected boolean incoming;
}
