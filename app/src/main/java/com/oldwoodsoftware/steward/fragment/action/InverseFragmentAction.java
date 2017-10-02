package com.oldwoodsoftware.steward.fragment.action;

import com.oldwoodsoftware.steward.core.calculation.UnitConverter;
import com.oldwoodsoftware.steward.fragment.base_listener.InverseFragmentListener;
import com.oldwoodsoftware.steward.fragment.base.InverseFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.component.PlateConfiguration;
import com.oldwoodsoftware.steward.platform.event.PlateEventListener;
import com.oldwoodsoftware.steward.platform.type.Configuration;

public class InverseFragmentAction extends FragmentAction implements InverseFragmentListener, PlateEventListener {
    InverseFragment own;
    PlateConfiguration plateConfiguration;
    UnitConverter unitConverter;

    public InverseFragmentAction(InverseFragment fragment){
        own = fragment;
    }

    @Override
    public void onInverseFragmentSliderChange(int[] new_progresses) {
        //pContext.getIK().calculateCurrentXYZABCvalues(new_progresses);
        //float[] realValues = pContext.getIK().getCurrentXYZABCvalues();
        //String[] strings = pContext.getIK().getStringRepresentation();

        //setInverseFragmentSliderTexts(strings);

        //TODO: btConnection
        try {
         //   pContext.getCmdProtocol().putInverseCommand(realValues);
        } catch (Exception e) { }
    }

    @Override
    public void setInverseFragmentSliderTexts(String[] texts) {
        try {
            own.getInverseAdapter().setSliderText(texts);
        }catch(IllegalStateException ex){ }
        catch(NullPointerException ex){}
    }

    @Override
    public String[] getInverseFragmentSliderTexts() {
        return null;//pContext.getIK().getStringRepresentation();
    }

    @Override
    public int[] getCurrentSliderProgresses() {
        return null;// pContext.getIK().getPromilesFromCurrentXYZABCvalues();
    }

    @Override
    public void onZeroButtonPressed() {
        try {
            //pContext.getCmdProtocol().putInverseCommand(new float[] {0f, 0f, 0f, 0f, 0f, 0f} );
        } catch (Exception e) {
        }
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
}
