package com.oldwoodsoftware.steward.old_model.responsibility.patron;

import com.oldwoodsoftware.steward.old_view.listelement.ToggleListElement;

public interface TogglePatron {
    void onButtonToggled(ToggleListElement sender);
    void setButtonText(String texts);
}
