package com.oldwoodsoftware.steward.fragment;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.MainActivity;
import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.gui.NonSwipeableViewPager;
import com.oldwoodsoftware.steward.fragment.gui.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.fragment.base.AccelerometerFragment;
import com.oldwoodsoftware.steward.fragment.base.DebugFragment;
import com.oldwoodsoftware.steward.fragment.base.InverseFragment;
import com.oldwoodsoftware.steward.fragment.base.SettingsFragment;
import com.oldwoodsoftware.steward.fragment.base.StatusFragment;
import com.oldwoodsoftware.steward.fragment.base.TargetFragment;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class FragmentApplication {
    private FragmentActionManager fragmentActionManager;
    private PlatformContext platformContext;

    private SettingsFragment settingsFragment = new SettingsFragment();
    private InverseFragment inverseFragment = new InverseFragment();
    private AccelerometerFragment accelerometerFragment = new AccelerometerFragment();
    private TargetFragment targetFragment = new TargetFragment();
    private DebugFragment debugFragment = new DebugFragment();
    private StatusFragment statusFragment = new StatusFragment();

    public FragmentApplication(MainActivity activity){
        platformContext = activity.getPlatformContext();

        //Tabs fragments
        ViewPager viewPager = (NonSwipeableViewPager) activity.findViewById(R.id.viewpager);
        StewardFragmentPagerAdapter fragmentAdapter = new StewardFragmentPagerAdapter(activity.getSupportFragmentManager(),
                settingsFragment, inverseFragment, accelerometerFragment, targetFragment, debugFragment);
        viewPager.setAdapter(fragmentAdapter);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) activity.findViewById(R.id.tabs);
        tabsStrip.setShouldExpand(true);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setIndicatorColor(Color.parseColor("#101082"));

        // Status fragment
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.statusFrame, statusFragment);
        ft.commit();

        //All fragments action
        fragmentActionManager = new FragmentActionManager(settingsFragment, inverseFragment, accelerometerFragment, targetFragment, debugFragment, statusFragment);
        fragmentActionManager.connectListenersWithPlatform(platformContext);
        fragmentActionManager.activeFragmentsAction(platformContext);
    }

}
