package com.oldwoodsoftware.steward.old_model.responsibility.patron;

import com.oldwoodsoftware.steward.old_view.listelement.ButtonListElement;

public interface ButtonPatron {
    void onButtonPressed(ButtonListElement sender);
    void setButtonText(String texts);
}
