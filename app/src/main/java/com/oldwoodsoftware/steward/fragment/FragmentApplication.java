package com.oldwoodsoftware.steward.fragment;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

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
        Log.i("ApplicationBuild","FragmentApplication() called. Visual side of app is about to be built");
        Log.i("ApplicationBuild","All base.Fragments are instantiated.");
        platformContext = activity.getPlatformContext();

        //Tabs fragments
        Log.i("ApplicationBuild","FragmentApplication(): Creating application tabs.");
        ViewPager viewPager = (NonSwipeableViewPager) activity.findViewById(R.id.viewpager);
        StewardFragmentPagerAdapter fragmentAdapter = new StewardFragmentPagerAdapter(activity.getSupportFragmentManager(),
                settingsFragment, inverseFragment, accelerometerFragment, targetFragment, debugFragment);
        Log.i("ApplicationBuild",fragmentAdapter.toString());
        viewPager.setAdapter(fragmentAdapter);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) activity.findViewById(R.id.tabs);
        tabsStrip.setShouldExpand(true);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setIndicatorColor(Color.parseColor("#101082"));

        // Status fragment
        Log.i("ApplicationBuild","FragmentApplication(): Committing Status Bar Fragment to activity");
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.statusFrame, statusFragment);
        ft.commit();

        //All fragments action
        Log.i("ApplicationBuild","FragmentApplication(): Interaction side of fragments are about to be built");
        fragmentActionManager = new FragmentActionManager(settingsFragment, inverseFragment, accelerometerFragment, targetFragment, debugFragment, statusFragment);
        fragmentActionManager.connectListenersWithPlatform(platformContext);
        fragmentActionManager.activeFragmentsAction(platformContext);

        Log.i("ApplicationBuild","FragmentApplication(): Connections between FragmentActions and PlatformComponents are: ");
        Log.i("ApplicationBuild",platformContext.getBallOnPlate().toString());
        Log.i("ApplicationBuild",platformContext.getStateMachine().toString());
        Log.i("ApplicationBuild",platformContext.getGeneralSystem().toString());
        Log.i("ApplicationBuild",platformContext.getPlateConfiguration().toString());
        Log.i("ApplicationBuild",platformContext.getPidControlX().toString());
        Log.i("ApplicationBuild",platformContext.getPidControlY().toString());
        Log.i("ApplicationBuild",platformContext.getBluetoothConnection().toString());
    }

}
