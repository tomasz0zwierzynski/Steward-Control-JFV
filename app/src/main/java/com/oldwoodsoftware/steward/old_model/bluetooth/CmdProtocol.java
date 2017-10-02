package com.oldwoodsoftware.steward.old_model.bluetooth;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.old_model.responsibility.listener.BluetoothDataListener;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothConnection;

import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class CmdProtocol implements BluetoothDataListener {

    private BluetoothConnection btCon;

    private CommandTypeMode ctMode = CommandTypeMode.demo;
    private boolean isModeStarted = false;

    private boolean isTransferUnlocked = true;
    private Timer timer;
    private final int TRANSFER_INTERVAL_MS = 50;

    private CommandReciever commandReciever;
    private PlatformContext pContext;

    public CmdProtocol(BluetoothConnection bluetoothConnection, PlatformContext context) throws Exception{
        if (bluetoothConnection == null){
            throw new Exception("Bluetooth connection not established");
        }

        btCon = bluetoothConnection;
        pContext = context;
        commandReciever = new CommandReciever(pContext);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Timer run()");
                isTransferUnlocked = true;
            }
        },TRANSFER_INTERVAL_MS,TRANSFER_INTERVAL_MS);
    }

    public void putCommand(Command... cmds) throws Exception{
        for( Command cmd: cmds){
            String command = cmd.commandType.get_uC_command_code_as_string() + "=" + String.valueOf(cmd.value);
            btCon.sendMessage(command.getBytes());
        }
    }

    public void putInverseCommand(float[] values) throws Exception{
        if (isTransferUnlocked) {
            if (ctMode != CommandTypeMode.ikMode) {
                setPlatformMode(CommandTypeMode.ikMode);
            }
            putMoveToCommand();
            for (int i = 0; i < values.length; i++) {
                String command = String.valueOf(CommandType.setIkX.get_uC_command_code() + i) + "=" + String.valueOf(values[i]);
                btCon.sendMessage(command.getBytes());
            }
            putSubmitCommand();
            isTransferUnlocked = false;
        }
    }

    public void putAccelerometerCommand(float pitch, float roll) throws Exception{
        if (isTransferUnlocked) {
            if (ctMode != CommandTypeMode.ikMode) {
                setPlatformMode(CommandTypeMode.ikMode);
            }
            putMoveToCommand();
            String command = CommandType.setIkPitch.get_uC_command_code_as_string() + "=" + String.valueOf(pitch);
            btCon.sendMessage(command.getBytes());
            command = CommandType.setIkRoll.get_uC_command_code_as_string() + "=" + String.valueOf(roll);
            btCon.sendMessage(command.getBytes());
            putSubmitCommand();
            isTransferUnlocked = false;
        }
    }

    //X is swaped with Y intentionally
    public void putTargetCommand(float x, float y) throws Exception{
        if (isTransferUnlocked) {
            if (ctMode != CommandTypeMode.pidMode) {
                setPlatformMode(CommandTypeMode.pidMode);
            }
            if (isModeStarted == false) {
                putStartModeCommand();
            }

            putMoveToCommand();
            String command = CommandType.setSetpointY.get_uC_command_code_as_string() + "=" + String.valueOf(-x);
            btCon.sendMessage(command.getBytes());
            command = CommandType.setSetpointX.get_uC_command_code_as_string() + "=" + String.valueOf(-y);
            btCon.sendMessage(command.getBytes());
            putSubmitCommand();
            isTransferUnlocked = false;
        }
    }

    public void putCommand(String command) throws Exception{
        btCon.sendMessage(command.getBytes());
    }

    //To test...
    public void setPlatformMode(CommandTypeMode mode) throws Exception{
        //here everything must be send to change mode (setMode, resetMode etc.)
        String msg = CommandType.setMode.get_uC_command_code_as_string() + "=" + mode.get_uC_mode_as_string();
        btCon.sendMessage(msg.getBytes());
        ctMode = mode;

        putStartModeCommand();
    }

    public void putMoveToCommand() throws Exception{
        String msg = CommandType.moveTo.get_uC_command_code_as_string() + "=0";
        btCon.sendMessage(msg.getBytes());
    }

    public void putSubmitCommand() throws  Exception{
        String msg = CommandType.submit.get_uC_command_code_as_string() + "=0";
        btCon.sendMessage(msg.getBytes());
    }

    public void putStartModeCommand() throws  Exception{
        String msg = CommandType.startMode.get_uC_command_code_as_string() + "=0";
        btCon.sendMessage(msg.getBytes());
        isModeStarted = true;
    }

    @Override
    public void onBluetoothData(byte[] data) {
        Command cmd = readCommand(data);
                //System.out.println("Recieved: " + new String(data, StandardCharsets.UTF_8));
        //TODO: decide what to do with data
        //TODO: what about expected returns

        commandReciever.recieveCommand(cmd);
    }

    @Override
    public void onBluetoothStateChanged(BluetoothStatus btStat) {

    }

    @Override
    public void onBluetoothMessage(String msg) {

    }

    public Command readCommand(byte[] bytes){
        String sBytes = new String(bytes, StandardCharsets.UTF_8);
        String sCommand = sBytes.substring(0, sBytes.indexOf('='));
        String sValue = sBytes.substring(sBytes.indexOf('=')+1, sBytes.length());

        int command = Integer.parseInt(sCommand);
        float value = Float.parseFloat(sValue);

        //TEMPORALY
        System.out.println("Recieved: " + CommandType.getCommandType(command).toString()+"="+String.valueOf(value));

        return new Command( CommandType.getCommandType(command),value) ;
    }
}
