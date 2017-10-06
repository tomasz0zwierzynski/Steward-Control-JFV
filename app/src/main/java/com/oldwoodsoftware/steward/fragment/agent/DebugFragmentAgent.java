package com.oldwoodsoftware.steward.fragment.agent;

public interface DebugFragmentAgent {
    void onDebugCommand(String cmd);
    void printCommandLine(String command);
}
