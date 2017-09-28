package com.oldwoodsoftware.steward.platform.type;

/**
 * Created by Nails on 28.09.2017.
 */

public enum PlatformMode {
    none(0),
    demoMode(1),
    pidMode(2),
    ikMode(3),
    servoMode(4),
    //etc
    ;

    private final int _uC_mode;

    PlatformMode(int uC_mode){
        _uC_mode = uC_mode;
    }

    public int get_uC_mode() {
        return _uC_mode;
    }

    public String get_uC_mode_as_string(){
        return String.valueOf(_uC_mode);
    }


    public static PlatformMode getMode(int i) {
        switch(i) {
            case 0:
                return PlatformMode.none;
            case 1:
                return PlatformMode.demoMode;
            case 2:
                return PlatformMode.pidMode;
            case 3:
                return PlatformMode.ikMode;
            case 4:
                return PlatformMode.servoMode;
            default:
                return PlatformMode.none;
        }
    }

}
