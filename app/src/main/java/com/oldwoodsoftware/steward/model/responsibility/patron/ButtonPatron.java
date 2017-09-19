package com.oldwoodsoftware.steward.model.responsibility.patron;

import com.oldwoodsoftware.steward.view.listelement.ButtonListElement;

public interface ButtonPatron {
    void onButtonPressed(ButtonListElement sender);
    void setButtonText(String texts);
}
