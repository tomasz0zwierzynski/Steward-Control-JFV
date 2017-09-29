package com.oldwoodsoftware.steward.fragment.base_listener;

public interface InverseFragmentListener {
    void onInverseFragmentSliderChange(int[] new_progresses);
    void setInverseFragmentSliderTexts(String[] texts);
    String[] getInverseFragmentSliderTexts();
    int[] getCurrentSliderProgresses();
    void onZeroButtonPressed();
}
