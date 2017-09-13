package com.oldwoodsoftware.steward.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oldwoodsoftware.steward.view.fragment.AccelerometerFragment;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;
import com.oldwoodsoftware.steward.view.fragment.InverseFragment;
import com.oldwoodsoftware.steward.view.fragment.SettingsFragment;
import com.oldwoodsoftware.steward.view.fragment.TargetFragment;

import java.util.ArrayList;
import java.util.List;

public class StewardFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public StewardFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<Fragment>();
        fragments.add(new SettingsFragment());
        fragments.add(new InverseFragment());
        fragments.add(new AccelerometerFragment());
        fragments.add(new TargetFragment());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
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
