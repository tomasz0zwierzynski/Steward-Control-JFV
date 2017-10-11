package com.oldwoodsoftware.steward.platform.component;

import android.util.Log;

import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;

import java.util.ArrayList;
import java.util.List;

public class PidControlCase {
    private float setpoint = 0;
    private int id;
    private List<PidControlEventListener> pidControlEventListeners = new ArrayList<>();

    public PidControlCase(int id){
        this.id = id;
    }

    public void setSetpoint(float setpoint) {
        this.setpoint = setpoint;
        emitSetpointChanged();
    }

    public float getSetpoint() {
        return setpoint;
    }

    public void addPidControlEventListener(PidControlEventListener pcl){
        pidControlEventListeners.add(pcl);
    }

    private void emitSetpointChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + " from " + this.hashCode() + " #.setpointChanged emitting...");
        for (PidControlEventListener pcl : pidControlEventListeners){
            pcl.onPidSetpointChanged(id,setpoint);
        }
    }

    @Override
    public String toString(){
        return "platform.component." + this.getClass().getSimpleName() + ", registered listeners " + pidControlEventListeners.toString();
    }

}
