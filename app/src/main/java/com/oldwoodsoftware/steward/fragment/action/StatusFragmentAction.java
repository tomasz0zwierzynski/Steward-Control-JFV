package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.fragment.base.StatusFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class StatusFragmentAction extends FragmentAction {
    StatusFragment own;

    public StatusFragmentAction(StatusFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        isActive = true;
    }
}
