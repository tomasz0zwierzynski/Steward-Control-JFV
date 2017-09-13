package com.slinkwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.slinkwoodsoftware.steward.fragment.AccelerometerFragment;
import com.slinkwoodsoftware.steward.fragment.FragmentType;
import com.slinkwoodsoftware.steward.fragment.StewardFragmentPagerAdapter;
import com.slinkwoodsoftware.steward.model.StatusBarUpdater;
import com.slinkwoodsoftware.steward.fragment.listener.AccelerometerFragmentStateListener;
import com.slinkwoodsoftware.steward.sensor.AccelerometerHandler;
import com.slinkwoodsoftware.steward.sensor.AccelerometerHandlerListener;

public class MainActivity extends AppCompatActivity implements AccelerometerFragmentStateListener, AccelerometerHandlerListener {

    private StewardFragmentPagerAdapter fragmentAdapter;
    private StatusBarUpdater statusBar;
    private AccelerometerHandler accHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentAdapter = new StewardFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

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

        //Creating StatusBarUpdater
        statusBar = new StatusBarUpdater(this);

        //Creating Accelerometer handler
        accHandler = new AccelerometerHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        accHandler.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        accHandler.onResume();
    }


    //Accelerometer interface implementations
    //AccelerometerFragmentStateListener interface
    public void onAccelerometerFragmentStateChange(boolean state){
        if (state) { //active
            accHandler.startRead();
        }else{
            accHandler.stopRead();
        }
    }

    //AccelerometerHandlerListener interface
    public void onAccelerometerHandlerNewData(float pitch, float roll){
        //TODO: Bluetooth Transmit
        try {
            ((AccelerometerFragment) fragmentAdapter.getItem(FragmentType.Accelerometer)).updateControls(pitch,roll);
        }catch(IllegalStateException ex){ }
    }
}
