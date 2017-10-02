package com.oldwoodsoftware.steward.fragment.gui.listpatron;

import com.oldwoodsoftware.steward.fragment.gui.listelement.ButtonListElement;

public interface ButtonPatron {
    void onButtonPressed(ButtonListElement sender);
    void setButtonText(String texts);
}
