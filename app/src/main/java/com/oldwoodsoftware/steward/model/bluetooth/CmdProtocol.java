package com.oldwoodsoftware.steward.model.bluetooth;

public class CmdProtocol {

    private BluetoothConnection btCon;

    public CmdProtocol(BluetoothConnection bluetoothConnection) throws Exception{
        if (bluetoothConnection == null){
            throw new Exception("Bluetooth connection not established");
        }

        btCon = bluetoothConnection;
    }

    public void putCommand(Command... cmds) throws Exception{
        for( Command cmd: cmds){
            String command = cmd.commandType.get_uC_command_code_as_string() + "=" + String.valueOf(cmd.value);
            btCon.sendMessage(command.getBytes());
        }
    }

    public void putInverseCommand(float[] values) throws Exception{
        for( int i=0;i<values.length;i++ ){
            String command = String.valueOf(CommandType.setX.get_uC_command_code() + i) + "=" + String.valueOf(values[i]);
            btCon.sendMessage(command.getBytes());
        }
    }

    public void putAccelerometerCommand(float pitch, float roll) throws Exception{
        String command = CommandType.setPitch.get_uC_command_code_as_string() + "=" + String.valueOf(pitch);
        btCon.sendMessage(command.getBytes());
        command = CommandType.setRoll.get_uC_command_code_as_string() + "=" + String.valueOf(roll);
        btCon.sendMessage(command.getBytes());
    }

    public void putTargetCommand(float x, float y) throws Exception{
        String command = CommandType.setTargetX.get_uC_command_code_as_string() + "=" + String.valueOf(x);
        btCon.sendMessage(command.getBytes());
        command = CommandType.setTargetY.get_uC_command_code_as_string() + "=" + String.valueOf(y);
        btCon.sendMessage(command.getBytes());
    }
}
