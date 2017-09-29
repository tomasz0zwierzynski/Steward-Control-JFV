package com.oldwoodsoftware.steward.fragment.base_listener;

public interface TargetFragmentListener {
    void onNewTargetPosition(float x_per, float y_per);
    void setCurrentBallPosition(float x_per, float y_per, boolean show);
    float getPanelLenghtRatio();
}
