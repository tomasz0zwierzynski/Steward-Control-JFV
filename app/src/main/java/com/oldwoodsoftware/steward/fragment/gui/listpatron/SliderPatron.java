package com.oldwoodsoftware.steward.fragment.gui.listpatron;

import com.oldwoodsoftware.steward.fragment.gui.listelement.SliderListElement;

public interface SliderPatron {
    void onSliderProgressChanged(SliderListElement sender);
    void setSliderText(String[] texts);
}
