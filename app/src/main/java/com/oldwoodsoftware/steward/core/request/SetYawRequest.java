package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nails on 05.10.2017.
 */

public class SetYawRequest extends AbstractRequest {

    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        List<AbstractCommand> cmds = new ArrayList<>();
        AbstractRequest.addIkModeWrapper(cmds,stateMachine,cmdCreate);
        cmds.add(cmdCreate.createCommand(CommandType.setIkYaw,value,false));
        return cmds;
    }

    public SetYawRequest(float value) {
        super(value);
    }


}
