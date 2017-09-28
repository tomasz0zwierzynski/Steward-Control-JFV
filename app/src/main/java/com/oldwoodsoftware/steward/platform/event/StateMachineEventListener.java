package com.oldwoodsoftware.steward.platform.event;

import com.oldwoodsoftware.steward.platform.type.PlatformMode;

/**
 * Created by Nails on 28.09.2017.
 */

public interface StateMachineEventListener {
    void onStateMachineModeChanged(PlatformMode mode);
}
