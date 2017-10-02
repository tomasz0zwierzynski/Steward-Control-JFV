package com.oldwoodsoftware.steward.core;

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

    }

    public void init(PlatformContext platformContext){
        commandExecutor = new CommandExecutor();
        commandFactory = new CommandFactory(platformContext);
        commandParser = new CommandParser(platformContext.getStateMachine(),commandFactory,commandExecutor);
        bluetoothReceiver = new BluetoothReceiver(commandFactory, commandExecutor);
        unitConverter = new UnitConverter(platformContext.getPlatformParameters());

        connectListenersWithCore(platformContext);
    }

    public void connectListenersWithCore(PlatformContext pContext){
        pContext.getBluetoothConnection().addBluetoothListener(bluetoothReceiver);
        pContext.getBluetoothConnection().addBluetoothListener(commandParser);
        pContext.getStateMachine().addStateMachineEventListener(commandParser);
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
