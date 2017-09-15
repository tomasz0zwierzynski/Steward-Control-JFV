package com.oldwoodsoftware.steward.model.responsibility.listener;

public interface InverseFragmentSliderListener {
    void onInverseFragmentSliderChange(int[] new_progresses);
    void setInverseFragmentSliderTexts(String[] texts);
    String[] getInverseFragmentSliderTexts();
    int[] getCurrentSliderProgresses();
}
