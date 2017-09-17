package com.oldwoodsoftware.steward.model.bluetooth;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.responsibility.listener.BluetoothDataListener;

import java.nio.charset.StandardCharsets;

public class CmdProtocol implements BluetoothDataListener {

    private BluetoothConnection btCon;

    private CommandTypeMode ctMode = CommandTypeMode.zero;

    private PlatformContext pContext;

    public CmdProtocol(BluetoothConnection bluetoothConnection, PlatformContext context) throws Exception{
        if (bluetoothConnection == null){
            throw new Exception("Bluetooth connection not established");
        }

        btCon = bluetoothConnection;
        pContext = context;
    }

    public void putCommand(Command... cmds) throws Exception{
        for( Command cmd: cmds){
            String command = cmd.commandType.get_uC_command_code_as_string() + "=" + String.valueOf(cmd.value);
            btCon.sendMessage(command.getBytes());
        }
    }

    public void putInverseCommand(float[] values) throws Exception{
        if (ctMode != CommandTypeMode.ik){
            setPlatformMode(CommandTypeMode.ik);
        }
        for( int i=0;i<values.length;i++ ){
            String command = String.valueOf(CommandType.setX.get_uC_command_code() + i) + "=" + String.valueOf(values[i]);
            btCon.sendMessage(command.getBytes());
        }
    }

    public void putAccelerometerCommand(float pitch, float roll) throws Exception{
        if (ctMode != CommandTypeMode.ik){
            setPlatformMode(CommandTypeMode.ik);
        }
        String command = CommandType.setPitch.get_uC_command_code_as_string() + "=" + String.valueOf(pitch);
        btCon.sendMessage(command.getBytes());
        command = CommandType.setRoll.get_uC_command_code_as_string() + "=" + String.valueOf(roll);
        btCon.sendMessage(command.getBytes());
    }

    //X is swaped with Y intentionally
    public void putTargetCommand(float x, float y) throws Exception{
        if (ctMode != CommandTypeMode.target){
            setPlatformMode(CommandTypeMode.target);
        }
        String command = CommandType.setTargetY.get_uC_command_code_as_string() + "=" + String.valueOf(x);
        btCon.sendMessage(command.getBytes());
        command = CommandType.setTargetX.get_uC_command_code_as_string() + "=" + String.valueOf(-y);
        btCon.sendMessage(command.getBytes());
    }

    public Command readCommand(byte[] bytes){
        String sBytes = new String(bytes, StandardCharsets.UTF_8);
        String sCommand = sBytes.substring(0, sBytes.indexOf('='));
        String sValue = sBytes.substring(sBytes.indexOf('=')+1, sBytes.length());

        int command = Integer.parseInt(sCommand);
        float value = Float.parseFloat(sValue);

        return new Command( CommandType.getCommandType(command),value) ;
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
    }

    @Override
    public void onBluetoothData(byte[] data) {
        Command cmd = readCommand(data);

        System.out.println("Recieved: " + new String(data, StandardCharsets.UTF_8));
        //TODO: decide what to do with data
        if(cmd.commandType == CommandType.pidXerror){

            String sXerr = String.valueOf(cmd.value);
            pContext.getStatusBar().updatePlatfromStatus(0,sXerr);
        }else if(cmd.commandType == CommandType.pidYerror){
            String sYerr = String.valueOf(cmd.value);
            pContext.getStatusBar().updatePlatfromStatus(1,sYerr);
        }

    }
}
