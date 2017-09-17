package com.oldwoodsoftware.steward.model.responsibility.listener;

public interface DebugFragmentListener {
    void onDebugCommand(String cmd);
    void printCommandLine(String command);
}
