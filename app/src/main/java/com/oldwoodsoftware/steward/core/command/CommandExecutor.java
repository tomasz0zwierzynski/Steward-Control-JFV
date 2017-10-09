package com.oldwoodsoftware.steward.core.command;

import android.util.Log;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;

public class CommandExecutor implements CommandOutputQueuer, CommandInputQueuer {

    @Override
    public void addInputCommandToQueue(AbstractCommand cmd) {
        try {
            cmd.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOutputCommandToQueue(AbstractCommand cmd) {
        Log.i("CommandExecutor","addOutputCommandToQueue() called");
        try {
            cmd.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
