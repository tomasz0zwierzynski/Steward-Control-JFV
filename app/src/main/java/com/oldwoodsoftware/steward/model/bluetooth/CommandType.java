package com.oldwoodsoftware.steward.model.bluetooth;

public enum CommandType {
    empty(0),
    fail(1),
    ok(2),
    submit(3),
    cancel(4),
    startMode(5),
    stopMode(6),
    resetMode(7),

    moveTo(250),
    selectPid(251),

    setMode(500),
    setSetpointX(501),
    setSetpointY(502),
    setPidSamplingInterval(503),
    setKp(504),
    setKi(505),
    setKd(506),
    setN(507),
    setPidUpperLimit(508),
    setPidLowerLimit(509),
    setPidDeadband(510),
    setIkX(511),
    setIkY(512),
    setIkZ(513),
    setIkRoll(514),
    setIkPitch(515),
    setIkYaw(516),

    isPanelTouched(750),
    getPanelX(751),
    getPanelY(752),
    getMode(753),
    isModeWorking(754),
    getSetpointX(755),
    getSetpointY(756),
    getErrorX(757),
    getErrorY(758),
    getPidSamplingInterval(759),
    getPid(760),
    getKp(761),
    getKi(762),
    getKd(763),
    getN(764),
    getPidUpperLimit(765),
    getPidLowerLimit(766),
    getPidDeadband(767),
    isPidWorking(768),
    getIkX(769),
    getIkY(770),
    getIkZ(771),
    getIkRoll(772),
    getIkPitch(773),
    getIkYaw(774),
    getFreeHeap(775),
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
        switch (i){
            case 0:
                return CommandType.empty;
            case 1:
                return CommandType.fail;
            case 2:
                return CommandType.ok;
            case 3:
                return CommandType.submit;
            case 4:
                return CommandType.cancel;
            case 5:
                return CommandType.startMode;
            case 6:
                return CommandType.stopMode;
            case 7:
                return CommandType.resetMode;
            case 250:
                return CommandType.moveTo;
            case 251:
                return CommandType.selectPid;
            case 500:
                return CommandType.setMode;
            case 501:
                return CommandType.setSetpointX;
            case 502:
                return CommandType.setSetpointY;
            case 503:
                return CommandType.setPidSamplingInterval;
            case 504:
                return CommandType.setKp;
            case 505:
                return CommandType.setKi;
            case 506:
                return CommandType.setKd;
            case 507:
                return CommandType.setN;
            case 508:
                return CommandType.setPidUpperLimit;
            case 509:
                return CommandType.setPidLowerLimit;
            case 510:
                return CommandType.setPidDeadband;
            case 511:
                return CommandType.setIkX;
            case 512:
                return CommandType.setIkY;
            case 513:
                return CommandType.setIkZ;
            case 514:
                return CommandType.setIkRoll;
            case 515:
                return CommandType.setIkPitch;
            case 516:
                return CommandType.setIkYaw;
            case 750:
                return CommandType.isPanelTouched;
            case 751:
                return CommandType.getPanelX;
            case 752:
                return CommandType.getPanelY;
            case 753:
                return CommandType.getMode;
            case 754:
                return CommandType.isModeWorking;
            case 755:
                return CommandType.getSetpointX;
            case 756:
                return CommandType.getSetpointY;
            case 757:
                return CommandType.getErrorX;
            case 758:
                return CommandType.getErrorY;
            case 759:
                return CommandType.getPidSamplingInterval;
            case 760:
                return CommandType.getPid;
            case 761:
                return CommandType.getKp;
            case 762:
                return CommandType.getKi;
            case 763:
                return CommandType.getKd;
            case 764:
                return CommandType.getN;
            case 765:
                return CommandType.getPidUpperLimit;
            case 766:
                return CommandType.getPidLowerLimit;
            case 767:
                return CommandType.getPidDeadband;
            case 768:
                return CommandType.isPidWorking;
            case 769:
                return CommandType.getIkX;
            case 770:
                return CommandType.getIkY;
            case 771:
                return CommandType.getIkZ;
            case 772:
                return CommandType.getIkRoll;
            case 773:
                return CommandType.getIkPitch;
            case 774:
                return CommandType.getIkYaw;
            default:
                return CommandType.empty;
        }

    }

}