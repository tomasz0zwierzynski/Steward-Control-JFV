package com.oldwoodsoftware.steward.fragment.gui.listpatron;

import com.oldwoodsoftware.steward.fragment.gui.listelement.ToggleListElement;

public interface TogglePatron {
    void onButtonToggled(ToggleListElement sender);
    void setButtonText(String texts);
}
