package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.ButtonElement;

public interface ButtonPatron {
    void onButtonPressed(ButtonElement sender);
    void setButtonText(String[] texts);
}
