package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

import java.util.ArrayList;
import java.util.List;

public class SetTargetXRequest extends AbstractRequest{

    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        System.out.println("Debug: getCommandsBasedOnStateMachine() called on X");
        List<AbstractCommand> cmds = new ArrayList<>();
        AbstractRequest.addPidModeWrapper(cmds,stateMachine,cmdCreate);
        cmds.add(cmdCreate.createCommand(CommandType.setSetpointX,value,false));
        return cmds;
    }

    public SetTargetXRequest(float value) {
        super(value);
    }

}
