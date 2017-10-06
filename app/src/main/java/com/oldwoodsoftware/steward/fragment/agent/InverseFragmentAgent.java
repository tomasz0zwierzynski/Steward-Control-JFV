package com.oldwoodsoftware.steward.fragment.agent;

public interface InverseFragmentAgent {
    void onInverseFragmentSliderChange(int[] new_progresses);
    void setInverseFragmentSliderTexts(String[] texts);
    String[] getInverseFragmentSliderTexts();
    int[] getCurrentSliderProgresses();
    void onZeroButtonPressed();
}
