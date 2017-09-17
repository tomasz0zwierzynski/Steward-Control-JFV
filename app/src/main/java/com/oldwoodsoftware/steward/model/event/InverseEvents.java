package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;
import com.oldwoodsoftware.steward.view.fragment.InverseFragment;

public class InverseEvents extends FragmentEvent implements InverseFragmentSliderListener {

    InverseFragment own;
    PlatformContext pContext;

    public InverseEvents(InverseFragment fragment, PlatformContext context){
        own = fragment;
        pContext = context;
    }

    @Override
    public void onInverseFragmentSliderChange(int[] new_progresses) {
        pContext.getIK().calculateCurrentXYZABCvalues(new_progresses);
        float[] realValues = pContext.getIK().getCurrentXYZABCvalues();
        String[] strings = pContext.getIK().getStringRepresentation();

        setInverseFragmentSliderTexts(strings);

        //TODO: btConnection
        try {
            pContext.getCmdProtocol().putInverseCommand(realValues);
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
        return pContext.getIK().getStringRepresentation();
    }

    @Override
    public int[] getCurrentSliderProgresses() {
        return pContext.getIK().getPromilesFromCurrentXYZABCvalues();
    }
}
