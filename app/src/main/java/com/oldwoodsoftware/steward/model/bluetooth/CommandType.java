package com.oldwoodsoftware.steward.model.bluetooth;

public enum CommandType {
    zero(0),
    off(2),
    setX(3),
    setY(4),
    setZ(5),
    setRoll(6),
    setPitch(7),
    setYaw(8),
    setTargetX(31),
    setTargetY(32),
    pidXerror(33),
    pidYerror(34),
    setMode(50),
    //etc
    ;

    private final int _uC_command_code;

    CommandType(int uC_command_code){
        _uC_command_code = uC_command_code;
    }

    public int get_uC_command_code() {
        return _uC_command_code;
    }

    public String get_uC_command_code_as_string(){
        return String.valueOf(_uC_command_code);
    }

    public static CommandType getCommandType(int i) {
        switch(i) {
            case 33:
                return CommandType.pidXerror;
            case 34:
                return CommandType.pidYerror;
            default:
                return CommandType.zero;
        }
    }

}