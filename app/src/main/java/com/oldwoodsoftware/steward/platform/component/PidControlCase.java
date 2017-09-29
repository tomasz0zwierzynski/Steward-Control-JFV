package com.oldwoodsoftware.steward.platform.component;

import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nails on 28.09.2017.
 */

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
        for (PidControlEventListener pcl : pidControlEventListeners){
            pcl.onPidSetpointChanged(id,setpoint);
        }
    }
}
