package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.DebugFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.DebugFragment;

public class DebugFragmentEvents extends FragmentEvents implements DebugFragmentListener{
    DebugFragment own;

    public DebugFragmentEvents(DebugFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
    }

    @Override
    public void onDebugCommand(String cmd) {

        //TODO: debug command btComm
        try {
            pContext.getCmdProtocol().putCommand(cmd);
        } catch (Exception e) { }
    }

    @Override
    public void printCommandLine(String command) {
        own.println(command);
    }
}
