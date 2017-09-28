package com.oldwoodsoftware.steward.model.event;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.view.fragment.InverseFragment;

public class InverseFragmentEvents extends FragmentEvents implements InverseFragmentSliderListener {
    InverseFragment own;

    public InverseFragmentEvents(InverseFragment fragment, PlatformContext context){
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

    @Override
    public void onZeroButtonPressed() {
        try {
            pContext.getCmdProtocol().putInverseCommand(new float[] {0f, 0f, 0f, 0f, 0f, 0f} );
        } catch (Exception e) {
        }
    }
}
