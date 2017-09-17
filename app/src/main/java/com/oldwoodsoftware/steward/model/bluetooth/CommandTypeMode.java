package com.oldwoodsoftware.steward.model.bluetooth;

public enum CommandTypeMode {
    zero(0),
    demo(2),
    ik(3),
    calibration(4),
    target(5),
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
            case 2:
                return CommandTypeMode.demo;
            case 3:
                return CommandTypeMode.ik;
            case 4:
                return CommandTypeMode.calibration;
            case 5:
                return CommandTypeMode.target;
            default:
                return CommandTypeMode.zero;
        }
    }

}
