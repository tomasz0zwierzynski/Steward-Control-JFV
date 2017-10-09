package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.core.calculation.UnitConverter;
import com.oldwoodsoftware.steward.fragment.agent.InverseFragmentAgent;
import com.oldwoodsoftware.steward.fragment.base.InverseFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.component.PlateConfiguration;
import com.oldwoodsoftware.steward.platform.event.PlateEventListener;
import com.oldwoodsoftware.steward.platform.type.Configuration;

public class InverseFragmentAction extends FragmentAction implements InverseFragmentAgent, PlateEventListener {
    InverseFragment own;
    PlateConfiguration plateConfiguration;
    UnitConverter unitConverter;

    public InverseFragmentAction(InverseFragment fragment){
        own = fragment;
    }


    @Override
    public void onPlateConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void activate(PlatformContext pContext) {
        plateConfiguration = pContext.getPlateConfiguration();
        unitConverter = pContext.getProcessContext().getUnitConverter();
        isActive = true;
    }

    @Override
    public void outSliderChanged(int index, int progress) {

    }

    @Override
    public void outZeroButtonPressed() {

    }

    @Override
    public float inGetSliderTextValue(int index) {
        return 0;
    }

    @Override
    public int inGetSliderProgress(int index) {
        return 0;
    }
}
