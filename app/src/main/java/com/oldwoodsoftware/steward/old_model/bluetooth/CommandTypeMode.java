package com.oldwoodsoftware.steward.old_model.bluetooth;

public enum CommandTypeMode {
    none(0),
    demo(1),
    pidMode(2),
    ikMode(3),
    servoMode(4),
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
                return CommandTypeMode.none;
            case 1:
                return CommandTypeMode.demo;
            case 2:
                return CommandTypeMode.pidMode;
            case 3:
                return CommandTypeMode.ikMode;
            case 4:
                return CommandTypeMode.servoMode;
            default:
                return CommandTypeMode.demo;
        }
    }

}
