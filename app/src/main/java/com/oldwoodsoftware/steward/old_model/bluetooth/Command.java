package com.oldwoodsoftware.steward.old_model.bluetooth;

public class Command {
    public CommandType commandType;
    public float value;

    public Command(CommandType commandType, float value){
        this.commandType = commandType;
        this.value = value;
    }
}
