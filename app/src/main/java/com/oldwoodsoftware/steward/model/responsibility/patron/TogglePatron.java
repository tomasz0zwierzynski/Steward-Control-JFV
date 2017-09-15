package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.ToggleElement;

public interface TogglePatron {
    void onButtonToggled(ToggleElement sender);
    void setButtonText(String texts);
}
