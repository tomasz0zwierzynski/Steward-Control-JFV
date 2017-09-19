package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEventManager;
import com.oldwoodsoftware.steward.model.event.FragmentEvents;
import com.oldwoodsoftware.steward.view.NonSwipeableViewPager;
import com.oldwoodsoftware.steward.view.fragment.*;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StewardFragmentPagerAdapter fragmentAdapter;

    //TODO: Wrap List to a FragmentEventsManager class with
    private List<FragmentEvents> fragmentEvents;
    private FragmentEventManager fragmentEventManager;

    private PlatformContext platformContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        platformContext = new PlatformContext(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
        fragmentAdapter = new StewardFragmentPagerAdapter(getSupportFragmentManager(),platformContext);
        viewPager.setAdapter(fragmentAdapter);

        fragmentEventManager = new FragmentEventManager(fragmentAdapter);
        //fragmentEvents = fragmentAdapter.createFragmentEvents();
        platformContext.setFragmentEventManager(fragmentEventManager);

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                try {
                    //that shoult not be here!!
                    ((AccelerometerFragment) fragmentAdapter.getItem(FragmentType.Accelerometer)).changeState(false);
                }catch(IllegalStateException ex){ }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Attach the view pager to the tab strip
        tabsStrip.setShouldExpand(true);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setIndicatorColor(Color.parseColor("#101082"));

    }

    //TODO: somewhere whem new XY ball value come up:
    // setCurrentBallPosition();
    // statusBar.setBall();

    //public List<FragmentEvents> getFragmentEvents(){
    //    return fragmentEvents;
    //}

    public FragmentEventManager getFragmentEventManager(){
        return fragmentEventManager;
    }

    @Override
    protected void onPause() {
        super.onPause();
        platformContext.getAccHandler().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        platformContext.getAccHandler().onResume();
    }

}
