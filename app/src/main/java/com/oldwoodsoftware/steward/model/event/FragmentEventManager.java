package com.oldwoodsoftware.steward.model.event;

import android.support.v4.app.Fragment;

import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;

import java.util.ArrayList;
import java.util.List;

public class FragmentEventManager {
    private List<FragmentEvents> fragmentEvents = new ArrayList<FragmentEvents>();

    public FragmentEventManager(StewardFragmentPagerAdapter sfpa){
        fragmentEvents = sfpa.createFragmentEvents();

        //if (fragmentEvents == null){
        //    System.out.println("TUTAJ JEST KURWA NULL");
        //}
    }

    public FragmentEvents getFragmentEvents(int at){
        return fragmentEvents.get(at);
    }

    public FragmentEvents getFragmentEvents(FragmentType ft){
        switch (ft){
            case Settings:
                return fragmentEvents.get(0);
            case Inverse:
                return fragmentEvents.get(1);
            case Accelerometer:
                return fragmentEvents.get(2);
            case Target:
                return fragmentEvents.get(3);
            case Debug:
                return fragmentEvents.get(4);
            default:
                return null;
        }
    }

}
