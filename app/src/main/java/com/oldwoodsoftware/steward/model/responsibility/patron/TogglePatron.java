package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.ToggleListElement;

public interface TogglePatron {
    void onButtonToggled(ToggleListElement sender);
    void setButtonText(String texts);
}
