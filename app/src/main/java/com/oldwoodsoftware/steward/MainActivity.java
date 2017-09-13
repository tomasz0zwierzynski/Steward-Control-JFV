package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.InverseKinematics;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.view.fragment.AccelerometerFragment;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.model.StatusBarUpdater;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerFragmentStateListener;
import com.oldwoodsoftware.steward.model.sensor.AccelerometerHandler;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerHandlerListener;
import com.oldwoodsoftware.steward.view.fragment.InverseFragment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements AccelerometerFragmentStateListener, AccelerometerHandlerListener, InverseFragmentSliderListener {

    private StewardFragmentPagerAdapter fragmentAdapter;
    private StatusBarUpdater statusBar;
    private AccelerometerHandler accHandler;

/*    private BluetoothConnection bluetoothConnection;
    private String deviceNamePrefix = "HC-05";
*/

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

        /*
        bluetoothConnection = new BluetoothConnection(this, new BluetoothConnection.MessageHandler() {
            @Override
            public int read(int bufferSize, byte[] buffer) {
                return doRead(bufferSize, buffer);
            }
        }, deviceNamePrefix);
*/

        // Attach the view pager to the tab strip
        tabsStrip.setShouldExpand(true);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setIndicatorColor(Color.parseColor("#101082"));

        //Creating StatusBarUpdater
        statusBar = new StatusBarUpdater(this);

        //Creating Accelerometer handler
        accHandler = new AccelerometerHandler(this);
    }

    private int doRead(int bufferSize, byte[] buffer) {
        Toast.makeText(getBaseContext(),new String(buffer, StandardCharsets.UTF_8),Toast.LENGTH_SHORT);

        return bufferSize;
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
    @Override
    public void onAccelerometerFragmentStateChange(boolean state){
        if (state) { //active
            accHandler.startRead();
        }else{
            accHandler.stopRead();
        }
    }

    //AccelerometerHandlerListener interface
    @Override
    public void onAccelerometerHandlerNewData(float pitch, float roll){
        //TODO: Bluetooth Transmit
        try {
            ((AccelerometerFragment) fragmentAdapter.getItem(FragmentType.Accelerometer)).updateControls(pitch,roll);
        }catch(IllegalStateException ex){ }
    }

    //Inverse interface implementations
    //InverseFragmentSliderListener
    @Override
    public void onInverseFragmentSliderChange(int[] new_progresses) {
        //Deal with int values of sliders eg. recalculate real values

        InverseKinematics ik = new InverseKinematics(getBaseContext(), new float[] {-20,-20,-20,-30,-30,-30}, new float[] {+20,+20,+20,+30,+30,+30} );
        ik.calculateCurrentXYZABCvalues(new_progresses);
        float[] realValues = ik.getCurrentXYZABCvalues();
        String[] strings = ik.getStringRepresentation();

        setInverseFragmentSliderTexts(strings);
        //bluetoothProtocol.sendInverse(realValues);
    }

    @Override
    public void setInverseFragmentSliderTexts(String[] texts) {
        try {
            ((InverseFragment) fragmentAdapter.getItem(FragmentType.Inverse)).getInverseAdapter().setSliderText(texts);
        }catch(IllegalStateException ex){ }
        catch(NullPointerException ex){}
    }


}
