package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.InverseKinematics;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.responsibility.listener.BluetoothDataListener;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.model.responsibility.listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.model.responsibility.listener.TargetFragmentListener;
import com.oldwoodsoftware.steward.view.fragment.AccelerometerFragment;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.model.StatusBarUpdater;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerFragmentStateListener;
import com.oldwoodsoftware.steward.model.sensor.AccelerometerHandler;
import com.oldwoodsoftware.steward.model.responsibility.listener.AccelerometerHandlerListener;
import com.oldwoodsoftware.steward.view.fragment.InverseFragment;
import com.oldwoodsoftware.steward.view.fragment.TargetFragment;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity
        implements AccelerometerFragmentStateListener, AccelerometerHandlerListener, InverseFragmentSliderListener, SettingsFragmentListener,
                    BluetoothDataListener, TargetFragmentListener {

    private StewardFragmentPagerAdapter fragmentAdapter;
    private StatusBarUpdater statusBar;
    private AccelerometerHandler accHandler;

    private BluetoothConnection btConnection;

    private InverseKinematics ik;
    /*
    private BluetoothSerial bluetoothConnection;
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

        // Attach the view pager to the tab strip
        tabsStrip.setShouldExpand(true);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setIndicatorColor(Color.parseColor("#101082"));

        //Creating StatusBarUpdater
        statusBar = new StatusBarUpdater(this);

        //Creating Accelerometer handler
        accHandler = new AccelerometerHandler(this);

        //IK paramters
        ik = new InverseKinematics(getBaseContext(), new float[] {-20,-20,-20,-30,-30,-30}, new float[] {+20,+20,+20,+30,+30,+30} );
    }

    private int doRead(int bufferSize, byte[] buffer) {
        //Toast.makeText(getBaseContext(),new String(buffer, StandardCharsets.UTF_8),Toast.LENGTH_SHORT);

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
        //bluetoothConnection.onResume();

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
        //TODO: Current values in IK must be updated over time
        ik.calculateCurrentXYZABCvalues(new_progresses);
        float[] realValues = ik.getCurrentXYZABCvalues();
        String[] strings = ik.getStringRepresentation();

        setInverseFragmentSliderTexts(strings);
        //bluetoothProtocol.sendInverse(realValues);
        try {
            btConnection.sendMessage( ("3=" + String.valueOf(realValues[0])).getBytes());
        } catch (Exception e) { }
    }

    @Override
    public void setInverseFragmentSliderTexts(String[] texts) {
        try {
            ((InverseFragment) fragmentAdapter.getItem(FragmentType.Inverse)).getInverseAdapter().setSliderText(texts);
        }catch(IllegalStateException ex){ }
        catch(NullPointerException ex){}
    }

    @Override
    public String[] getInverseFragmentSliderTexts() {
        return ik.getStringRepresentation();
    }

    @Override
    public int[] getCurrentSliderProgresses() {
        return ik.getPromilesFromCurrentXYZABCvalues();
    }

    //Settings interface implementations
    //SettingsFragmentListener
    @Override
    public void onBluetoothConnectionButtonChecked() {
        statusBar.updateBluetoothStatus(BluetoothStatus.Connecting);

        try {
            btConnection = new BluetoothConnection(this);
        } catch (Exception e) {
            statusBar.updateBluetoothStatus(e.getMessage());
        }


        try {
            if (btConnection.isConnected()) {
                statusBar.updateBluetoothStatus(BluetoothStatus.Connected);
            }
        }catch (Exception e){
            //statusBar.updateBluetoothStatus(e.getMessage());
        }
    }

    @Override
    public void onBluetoothConnectionButtonUnchecked() {

        statusBar.updateBluetoothStatus(BluetoothStatus.TurningOff);

        try {
            btConnection.disconnect();
        } catch (Exception e) {
            statusBar.updateBluetoothStatus(e.getMessage());
        }

        btConnection = null;
        statusBar.updateBluetoothStatus(BluetoothStatus.Disconnected);
    }

    @Override
    public boolean isBluetoothConnected() {
        if (btConnection == null){
            return false;
        }else{
            return true;
        }
    }

    public void onBluetoothData(byte[] data){
        statusBar.updateBluetoothStatus(new String(data, StandardCharsets.UTF_8));
    }

    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
        //TODO:Recalculate percentages to real values and btSend
        //propably there will be class handling metrics
    }

    @Override
    public void setCurrentBallPosition(float x_per, float y_per, boolean show) {
        //TODO:When new data about ball - call proper function
        //try {
        //    ((TargetFragment) fragmentAdapter.getItem(FragmentType.Target)).setCurrentBallPos();
        //}catch(IllegalStateException ex){ }
    }

    @Deprecated
    public void testMsg(){
        try {
            btConnection.sendMessage( "3=9.0;4=100.01".getBytes());
        } catch (Exception e) { }
    }


}
