package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

import java.util.ArrayList;
import java.util.List;

public class SetXRequest extends AbstractRequest {

    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        List<AbstractCommand> cmds = new ArrayList<>();
        AbstractRequest.addIkModeWrapper(cmds,stateMachine,cmdCreate);
        cmds.add(cmdCreate.createCommand(CommandType.setIkX,value,false));
        return cmds;
    }

    public SetXRequest(float value) {
        super(value);
    }

}
