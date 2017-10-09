package com.oldwoodsoftware.steward.fragment.agent;

public interface InverseFragmentAgent {
    void outSliderChanged(int index, int progress);
    void outZeroButtonPressed();
    float inGetSliderTextValue(int index);
    int inGetSliderProgress(int index);
}
