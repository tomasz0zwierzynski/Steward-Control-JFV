package com.oldwoodsoftware.steward.platform.event;

import com.oldwoodsoftware.steward.platform.type.PlatformMode;

public interface StateMachineEventListener {
    void onStateMachineModeChanged(PlatformMode mode);
    void onMoveToChanged(boolean isMoveTo);
    void onModeStartedChanged(boolean isModeStarted);
}
