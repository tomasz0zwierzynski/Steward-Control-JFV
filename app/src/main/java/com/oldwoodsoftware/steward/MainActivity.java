package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.model.InverseKinematics;
import com.oldwoodsoftware.steward.model.PanelGeometrics;
import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.model.bluetooth.CmdProtocol;
import com.oldwoodsoftware.steward.model.bluetooth.Command;
import com.oldwoodsoftware.steward.model.bluetooth.CommandType;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;
import com.oldwoodsoftware.steward.model.responsibility.listener.*;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.view.fragment.*;
import com.oldwoodsoftware.steward.view.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.model.StatusBarUpdater;
import com.oldwoodsoftware.steward.model.sensor.AccelerometerHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StewardFragmentPagerAdapter fragmentAdapter;

    private BluetoothConnection btConnection;
    private CmdProtocol cmdProtocol;

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

    //Settings interface implementations
    //SettingsFragmentListener
    /*
    @Override
    public void onBluetoothConnectionButtonChecked() {
        platformContext.statusBar.updateBluetoothStatus(BluetoothStatus.Connecting);

        try {
            btConnection = new BluetoothConnection(this);
            cmdProtocol = new CmdProtocol(btConnection);
        } catch (Exception e) {
            platformContext.statusBar.updateBluetoothStatus(e.getMessage());
        }

        try {
            if (btConnection.isConnected()) {
                platformContext.statusBar.updateBluetoothStatus(BluetoothStatus.Connected);
            }
        }catch (Exception e){
            //statusBar.updateBluetoothStatus(e.getMessage());
        }
    }

    @Override
    public void onBluetoothConnectionButtonUnchecked() {

        platformContext.statusBar.updateBluetoothStatus(BluetoothStatus.TurningOff);

        try {
            btConnection.disconnect();
        } catch (Exception e) {
            platformContext.statusBar.updateBluetoothStatus(e.getMessage());
        }

        btConnection = null;
        cmdProtocol = null;
        platformContext.statusBar.updateBluetoothStatus(BluetoothStatus.Disconnected);
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
        //TODO: data reciever its temporary here now
        Command retrieve = cmdProtocol.readCommand(data);

        if(retrieve.commandType == CommandType.pidXerror){

            String sXerr = String.valueOf(retrieve.value);
            platformContext.statusBar.updatePlatfromStatus(0,sXerr);
        }else if(retrieve.commandType == CommandType.pidYerror){
            String sYerr = String.valueOf(retrieve.value);
            platformContext.statusBar.updatePlatfromStatus(1,sYerr);
        }
        //statusBar.updateBluetoothStatus(new String(data, StandardCharsets.UTF_8));
    }
    */
/*
    @Override
    public void onNewTargetPosition(float x_per, float y_per) {
        platformContext.pg.calculateRealTargetXY(x_per,y_per);
        float x = platformContext.pg.getTargetX();
        float y = platformContext.pg.getTargetY();

        try {
            cmdProtocol.putTargetCommand(x,y);
        } catch (Exception e) { }
    }

    @Override
    public void setCurrentBallPosition(float x, float y, boolean show) {
        platformContext.pg.setBallXY(x,y);

        if(show){
            float[] per_XY = platformContext.pg.getPercentBallXY();
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
        return platformContext.pg.getRatio();
    }
*/
    //TODO: somewhere whem new XY ball value come up:
    // setCurrentBallPosition();
    // statusBar.setBall();

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

    @Deprecated
    public void testMsg(){

        try {
            cmdProtocol.putCommand(new Command(CommandType.off,0));
        } catch (Exception e) { }

        /*
        try {
            btConnection.sendMessage( "3=9.0;4=100.01".getBytes());
        } catch (Exception e) { }
        */
    }

    /*
    @Override
    public void onDebugCommand(String cmd) {
        try {
            cmdProtocol.putCommand(cmd);
        } catch (Exception e) { }
    }
    */
}
