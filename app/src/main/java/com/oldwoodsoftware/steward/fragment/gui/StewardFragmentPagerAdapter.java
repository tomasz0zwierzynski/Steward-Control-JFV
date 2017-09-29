package com.oldwoodsoftware.steward.fragment.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oldwoodsoftware.steward.fragment.base.GeneralFragment;

import java.util.ArrayList;
import java.util.List;

public class StewardFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<GeneralFragment> tabbedFragments = new ArrayList<GeneralFragment>();

    public StewardFragmentPagerAdapter(FragmentManager fm, GeneralFragment... tabFragments) {
        super(fm);
        for (GeneralFragment gf : tabFragments){
            tabbedFragments.add(gf);
        }
    }

    @Override
    public int getCount() {
        return tabbedFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return tabbedFragments.get(position);
    }

    /*
    public List<FragmentAction> createFragmentEvents() {
        List<FragmentAction> fragmentEvents = new ArrayList<FragmentAction>();
        for (GeneralFragment gf: fragments){
            FragmentAction fe = gf.createFragmentEvent(pContext);
            gf.addFragmentListener(fe);
            fragmentEvents.add( fe );
        }
        return fragmentEvents;
    }
    */

    @Override
    public CharSequence getPageTitle(int position) {
        return tabbedFragments.get(position).toString();
    }
}
