package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.platform.PlatformContext;

public abstract class FragmentAction {
    protected boolean isActive = false;
    public abstract void activate(PlatformContext pContext);
}
