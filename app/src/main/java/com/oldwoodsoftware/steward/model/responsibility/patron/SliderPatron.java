package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.SliderElement;

public interface SliderPatron {
    void onSliderProgressChanged(SliderElement sender);
    void setSliderText(String[] texts);
}
