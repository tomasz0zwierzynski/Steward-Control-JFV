package com.oldwoodsoftware.steward.model.bluetooth;

public enum CommandTypeMode {
    demo(0),
    pidMode(1),
    ikMode(2),
    servoMode(3),
    //etc
    ;

    private final int _uC_mode;

    CommandTypeMode(int uC_mode){
        _uC_mode = uC_mode;
    }

    public int get_uC_mode() {
        return _uC_mode;
    }

    public String get_uC_mode_as_string(){
        return String.valueOf(_uC_mode);
    }


    public static CommandTypeMode getMode(int i) {
        switch(i) {
            case 0:
                return CommandTypeMode.demo;
            case 1:
                return CommandTypeMode.pidMode;
            case 2:
                return CommandTypeMode.ikMode;
            case 3:
                return CommandTypeMode.servoMode;
            default:
                return CommandTypeMode.demo;
        }
    }

}
