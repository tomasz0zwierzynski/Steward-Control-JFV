package com.oldwoodsoftware.steward.platform.component;

import android.util.Log;

import com.oldwoodsoftware.steward.platform.event.SystemEventListener;

import java.util.ArrayList;
import java.util.List;

public class GeneralSystem {
    private float cpuUsage = 0;
    private int freeHeap = 0;
    private List<SystemEventListener> systemEventListeners = new ArrayList<>();

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
        emitCpuUsageChanged();
    }

    public void setFreeHeap(int freeHeap) {
        this.freeHeap = freeHeap;
        emitFreeHeapChanged();
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public int getFreeHeap() {
        return freeHeap;
    }

    public void addSystemEventListener(SystemEventListener sel){
        systemEventListeners.add(sel);
    }

    private void emitCpuUsageChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".cpuUsageChanged emitting...");
        for(SystemEventListener sel : systemEventListeners){
            sel.onSystemCpuUsageChanged(cpuUsage);
        }
    }

    private void emitFreeHeapChanged(){
        Log.i("PlatformComponentSignal",this.getClass().getSimpleName() + ".freeHeapChanged emitting...");
        for(SystemEventListener sel : systemEventListeners){
            sel.onSystemFreeHeapChanged(freeHeap);
        }
    }

    @Override
    public String toString(){
        return "platform.component." + this.getClass().getSimpleName() + ", registered listeners " + systemEventListeners.toString();
    }

}
