package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.core.request.AbstractRequest;
import com.oldwoodsoftware.steward.platform.component.StateMachine;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;

import java.util.LinkedList;
import java.util.List;

public class CommandParser implements BluetoothEventListener{
    private StateMachine stateMachine;
    private CommandCreator cmdCreate;
    private CommandOutputQueuer outQueuer;

    private StateMachine requestStateMachine;
    private LinkedList<AbstractCommand> pendingCommands = new LinkedList<>();

    public CommandParser(StateMachine stateMachine, CommandCreator cmdCreate, CommandOutputQueuer outQueuer){
        this.stateMachine = stateMachine;
        this.cmdCreate = cmdCreate;
        this.outQueuer = outQueuer;
        this.requestStateMachine = stateMachine.getCopy();
    }

    public void addRequest(AbstractRequest abstractRequest){
        System.out.println("Debug: addRequest() method called.");
        if (pendingCommands.isEmpty()){
            requestStateMachine = stateMachine.getCopy();
        }
        List<AbstractCommand> commands = abstractRequest.getCommandsBasedOnStateMachine(requestStateMachine,cmdCreate);
        for(AbstractCommand cmd : commands){
            pendingCommands.add(cmd);
        }
    }

    public void pushRequests(){
        System.out.println("Debug: Printing pending Command List: ");
        for(AbstractCommand cmd : pendingCommands){
            System.out.println(" cmd ");
        }


        while (! pendingCommands.isEmpty()){
            outQueuer.addOutputCommandToQueue(pendingCommands.removeFirst());
        }
            outQueuer.addOutputCommandToQueue(cmdCreate.createCommand(CommandType.submit,0,false));
        //

        //stateMachine.setFromTemplate(requestStateMachine);
        //Commands while executing changes MachineState
        System.out.println("Debug: Printing pending Command List after: ");
        for(AbstractCommand cmd : pendingCommands){
            System.out.println(" cmd ");
        }

    }

    @Override
    public void onBluetoothConnectionStateChanged(BluetoothState btState) {
        if (btState == BluetoothState.disconnected){
            stateMachine.reset();
        }
    }

    @Override
    public void onBluetoothDataReceived(byte[] data) {

    }

    @Override
    public void onBluetoothMessage(String msg) {

    }
}
