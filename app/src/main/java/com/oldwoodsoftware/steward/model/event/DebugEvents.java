package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.DebugFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.DebugFragment;

public class DebugEvents extends FragmentEvent implements DebugFragmentListener{

    DebugFragment own;
    PlatformContext pContext;

    public DebugEvents(DebugFragment fragment, PlatformContext context){
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
}
