package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.fragment.base_listener.DebugFragmentListener;
import com.oldwoodsoftware.steward.fragment.base.DebugFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.component.PlateConfiguration;

public class DebugFragmentAction extends FragmentAction implements DebugFragmentListener{
    DebugFragment own;

    public DebugFragmentAction(DebugFragment fragment){
        own = fragment;
    }

    @Override
    public void activate(PlatformContext pContext){
        isActive = true;
    }

    @Override
    public void onDebugCommand(String cmd) {

        //TODO: debug command btComm
        try {
           // pContext.getCmdProtocol().putCommand(cmd);
        } catch (Exception e) { }
    }

    @Override
    public void printCommandLine(String command) {
        own.println(command);
    }
}
