package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;
import com.oldwoodsoftware.steward.view.fragment.*;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StewardFragmentPagerAdapter fragmentAdapter;

    private List<FragmentEvent> fragmentEvents;
    private PlatformContext platformContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        platformContext = new PlatformContext(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentAdapter = new StewardFragmentPagerAdapter(getSupportFragmentManager(),platformContext);
        viewPager.setAdapter(fragmentAdapter);

        fragmentEvents = fragmentAdapter.createFragmentEvents();

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                try {
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

    public List<FragmentEvent> getFragmentEvents(){
        return fragmentEvents;
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
