package com.oldwoodsoftware.steward.core;

import android.util.Log;

import com.oldwoodsoftware.steward.core.bluetooth.BluetoothReceiver;
import com.oldwoodsoftware.steward.core.calculation.UnitConverter;
import com.oldwoodsoftware.steward.core.command.CommandExecutor;
import com.oldwoodsoftware.steward.core.command.CommandFactory;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class ProcessContext {
    private BluetoothReceiver bluetoothReceiver;
    private CommandExecutor commandExecutor;
    private CommandFactory commandFactory;
    private CommandParser commandParser;
    private UnitConverter unitConverter;

    public ProcessContext(){
        Log.i("ApplicationBuild","ProcessContext() called");
    }

    public void init(PlatformContext platformContext){
        Log.i("ApplicationBuild","ProcessContext.init() called");
        commandExecutor = new CommandExecutor();
        commandFactory = new CommandFactory(platformContext);
        commandParser = new CommandParser(platformContext.getStateMachine(),commandFactory,commandExecutor);
        bluetoothReceiver = new BluetoothReceiver(commandFactory, commandExecutor);
        unitConverter = new UnitConverter(platformContext.getPlatformParameters());
        Log.i("ApplicationBuild","ProcessContext (Core Components) components successfully created.");

        connectListenersWithCore(platformContext);
    }

    public void connectListenersWithCore(PlatformContext pContext){
        Log.i("ApplicationBuild","ProcessContext.connectListenersWithCore() called");
        pContext.getBluetoothConnection().addBluetoothListener(bluetoothReceiver);
        Log.i("ApplicationBuild","ProcessContext.connectListenersWithCore().BluetoothReceiver connected with BluetoothConnection");
        pContext.getBluetoothConnection().addBluetoothListener(commandParser);
        Log.i("ApplicationBuild","ProcessContext.connectListenersWithCore().BluetoothReceiver connected with CommandParser");
        //pContext.getStateMachine().addStateMachineEventListener(commandParser); //TODO:remove this line
    }

    public BluetoothReceiver getBluetoothReceiver() {
        return bluetoothReceiver;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public UnitConverter getUnitConverter() {
        return unitConverter;
    }
}
