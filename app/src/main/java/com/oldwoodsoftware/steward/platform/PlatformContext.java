package com.oldwoodsoftware.steward.platform;

import android.util.Log;

import com.oldwoodsoftware.steward.core.ProcessContext;
import com.oldwoodsoftware.steward.platform.component.BallOnPlate;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;
import com.oldwoodsoftware.steward.platform.component.GeneralSystem;
import com.oldwoodsoftware.steward.platform.component.PidControlCase;
import com.oldwoodsoftware.steward.platform.component.PlateConfiguration;
import com.oldwoodsoftware.steward.platform.component.PlatformParameters;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

public class PlatformContext {
    private BallOnPlate ballOnPlate;
    private PidControlCase pidControlX;
    private PidControlCase pidControlY;
    private PlateConfiguration plateConfiguration;
    private GeneralSystem generalSystem;
    private PlatformParameters platformParameters;
    private StateMachine stateMachine;
    private BluetoothConnection bluetoothConnection;

    private ProcessContext processContext;

    public PlatformContext(){
        Log.i("ApplicationBuild","PlatformContext() called");
    }

    public void init(){
        Log.i("ApplicationBuild","PlatformContext.init() called");
        ballOnPlate = new BallOnPlate();
        pidControlX = new PidControlCase(0);
        pidControlY = new PidControlCase(1);
        plateConfiguration = new PlateConfiguration();
        generalSystem = new GeneralSystem();
        platformParameters = new PlatformParameters(150f,100f,new float[]{-20,-20,-20,-12,-12,-12}, new float[]{+20,+20,+20,+12,+12,+12});
        stateMachine = new StateMachine();
        bluetoothConnection = new BluetoothConnection();
        Log.i("ApplicationBuild","PlatformContext (Platform Components) components successfully created.");

        processContext = new ProcessContext();
        processContext.init(this);
    }

    public BallOnPlate getBallOnPlate() {
        return ballOnPlate;
    }

    public PidControlCase getPidControlX() {
        return pidControlX;
    }

    public PidControlCase getPidControlY() {
        return pidControlY;
    }

    public PlateConfiguration getPlateConfiguration() {
        return plateConfiguration;
    }

    public GeneralSystem getGeneralSystem() {
        return generalSystem;
    }

    public PlatformParameters getPlatformParameters() {
        return platformParameters;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public BluetoothConnection getBluetoothConnection() {
        return bluetoothConnection;
    }

    public ProcessContext getProcessContext(){
        return processContext;
    }

}
