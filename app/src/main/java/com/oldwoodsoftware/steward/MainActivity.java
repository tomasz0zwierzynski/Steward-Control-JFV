package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.InverseKinematics;
import com.oldwoodsoftware.steward.model.PanelGeometrics;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.bluetooth.CmdProtocol;
import com.oldwoodsoftware.steward.model.responsibility.listener.*;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.view.fragment.*;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.model.StatusBarUpdater;
import com.oldwoodsoftware.steward.model.sensor.AccelerometerHandler;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity
        implements AccelerometerFragmentStateListener, AccelerometerHandlerListener, InverseFragmentSliderListener, SettingsFragmentListener,
                    BluetoothDataListener, TargetFragmentListener {

    private StewardFragmentPagerAdapter fragmentAdapter;
    private StatusBarUpdater statusBar;
    private AccelerometerHandler accHandler;

    private BluetoothConnection btConnection;
    private CmdProtocol cmdProtocol;

    private InverseKinematics ik;
    private PanelGeometrics pg;

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
        //Geometrical parameters
        pg = new PanelGeometrics(getBaseContext(), 297.0f, 210.0f); // default: 297.0f, 210.0f
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
        try {
            cmdProtocol.putAccelerometerCommand(pitch,roll);
        } catch (Exception e) { }
        try {
            ((AccelerometerFragment) fragmentAdapter.getItem(FragmentType.Accelerometer)).updateControls(pitch,roll);
        }catch(IllegalStateException ex){ }
    }

    //Inverse interface implementations
    //InverseFragmentSliderListener
    @Override
    public void onInverseFragmentSliderChange(int[] new_progresses) {
        //Deal with int values of sliders eg. recalculate real values
        //TODO: Current values in IK must be updated over time, when got new values of ik: ik.setcurrentxyzabcValues();
        ik.calculateCurrentXYZABCvalues(new_progresses);
        float[] realValues = ik.getCurrentXYZABCvalues();
        String[] strings = ik.getStringRepresentation();

        setInverseFragmentSliderTexts(strings);
        try {
            cmdProtocol.putInverseCommand(realValues);
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
            cmdProtocol = new CmdProtocol(btConnection);
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
        cmdProtocol = null;
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

    public void onBluetoothData(byte[] data){ //here we recieve command that is
        //TODO: data reciever

        statusBar.updateBluetoothStatus(new String(data, StandardCharsets.UTF_8));
    }

    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
        pg.calculateRealTargetXY(x_per,y_per);
        float x = pg.getTargetX();
        float y = pg.getTargetY();

        try {
            cmdProtocol.putTargetCommand(x,y);
        } catch (Exception e) { }
    }

    @Override
    public void setCurrentBallPosition(float x, float y, boolean show) {
        pg.setBallXY(x,y);

        if(show){
            float[] per_XY = pg.getPercentBallXY();
            try {
                ((TargetFragment) fragmentAdapter.getItem(FragmentType.Target)).onCurrentBallPositionChanged(per_XY[0],per_XY[1],true);
            }catch(IllegalStateException ex){ }
        }else{
            try {
                ((TargetFragment) fragmentAdapter.getItem(FragmentType.Target)).onCurrentBallPositionChanged(0,0,false);
            }catch(IllegalStateException ex){ }
        }
    }

    @Override
    public float getPanelLenghtRatio() {
        return pg.getRatio();
    }

    //TODO: somewhere whem new XY ball value come up:
    // setCurrentBallPosition();
    // statusBar.setBall();

    @Deprecated
    public void testMsg(){
        try {
            btConnection.sendMessage( "3=9.0;4=100.01".getBytes());
        } catch (Exception e) { }
    }

    @Deprecated
    public void testBall(){
        setCurrentBallPosition(0f,0f,true);
    }

}
