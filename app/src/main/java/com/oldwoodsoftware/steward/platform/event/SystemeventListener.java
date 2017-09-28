package com.oldwoodsoftware.steward.platform.event;

/**
 * Created by Nails on 28.09.2017.
 */

public interface SystemEventListener {
    void onSystemCpuUsageChanged(float cpuUsage);
    void onSystemFreeHeapChanged(int freeHeap);
}
