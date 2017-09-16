package com.oldwoodsoftware.steward.model.bluetooth;

public enum CommandType {
    setX(3),
    setY(4),
    setZ(5),
    setRoll(6),
    setPitch(7),
    setYaw(8),
    setTargetX(5000000),
    setTargetY(5000001),
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
}