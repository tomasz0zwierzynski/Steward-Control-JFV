package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.SliderListElement;

public interface SliderPatron {
    void onSliderProgressChanged(SliderListElement sender);
    void setSliderText(String[] texts);
}
