package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

import java.util.List;

/**
 * Created by Nails on 05.10.2017.
 */

public class NullRequest extends AbstractRequest {
    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        return null;
    }

    public NullRequest(float value) {
        super(value);
    }
}
