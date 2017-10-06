package com.oldwoodsoftware.steward.fragment.agent;

public interface TargetFragmentAgent {
    void onNewTargetPosition(float x_per, float y_per);
    //void setCurrentBallPosition(float x_per, float y_per, boolean show);
    float getPanelLenghtRatio();
}
