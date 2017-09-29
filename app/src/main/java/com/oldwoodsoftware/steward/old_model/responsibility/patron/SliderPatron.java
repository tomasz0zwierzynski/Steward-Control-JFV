package com.oldwoodsoftware.steward.old_model.responsibility.patron;

import com.oldwoodsoftware.steward.old_view.listelement.SliderListElement;

public interface SliderPatron {
    void onSliderProgressChanged(SliderListElement sender);
    void setSliderText(String[] texts);
}
