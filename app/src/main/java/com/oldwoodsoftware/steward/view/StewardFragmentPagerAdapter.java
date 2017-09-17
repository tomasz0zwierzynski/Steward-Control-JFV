package com.oldwoodsoftware.steward.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;
import com.oldwoodsoftware.steward.view.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class StewardFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<GeneralFragment> fragments;
    private PlatformContext pContext;

    public StewardFragmentPagerAdapter(FragmentManager fm, PlatformContext context) {
        super(fm);
        pContext = context;
        fragments = new ArrayList<GeneralFragment>();
        fragments.add(new SettingsFragment());
        fragments.add(new InverseFragment());
        fragments.add(new AccelerometerFragment());
        fragments.add(new TargetFragment());
        fragments.add(new DebugFragment());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public List<FragmentEvent> createFragmentEvents() {
        List<FragmentEvent> fragmentEvents = new ArrayList<FragmentEvent>();
        for (GeneralFragment gf: fragments){
            FragmentEvent fe = gf.createFragmentEvent(pContext);
            gf.addFragmentListener(fe);
            fragmentEvents.add( gf.createFragmentEvent(pContext) );
        }
        return fragmentEvents;
    }

    public Fragment getItem(FragmentType ft){
        switch (ft){
            case Settings:
                return fragments.get(0);
            case Inverse:
                return fragments.get(1);
            case Accelerometer:
                return fragments.get(2);
            case Target:
                return fragments.get(3);
            case Debug:
                return fragments.get(4);
            default:
                return new Fragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return fragments.get(position).toString();
    }
}
