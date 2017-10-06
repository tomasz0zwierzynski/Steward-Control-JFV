package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

import java.util.ArrayList;
import java.util.List;

public class SetZRequest extends AbstractRequest {

    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        List<AbstractCommand> cmds = new ArrayList<>();
        AbstractRequest.addIkModeWrapper(cmds,stateMachine,cmdCreate);
        cmds.add(cmdCreate.createCommand(CommandType.setIkZ,value,false));
        return cmds;
    }

    public SetZRequest(float value) {
        super(value);
    }


}
