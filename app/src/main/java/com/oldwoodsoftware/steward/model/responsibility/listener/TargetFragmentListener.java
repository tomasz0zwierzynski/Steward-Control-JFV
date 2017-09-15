package com.oldwoodsoftware.steward.model.responsibility.listener;

public interface TargetFragmentListener {
    void onNewTargetPosition(float x_per, float y_per);
    void setCurrentBallPosition(float x_per, float y_per, boolean show);
}
