package com.oldwoodsoftware.steward.fragment.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.StatusFragmentAction;
import com.oldwoodsoftware.steward.platform.component.PidControlCase;
import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;
import com.oldwoodsoftware.steward.platform.event.SystemEventListener;

public class StatusFragment extends GeneralFragment implements SystemEventListener, PidControlEventListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.statusbar_fragment, container, false);
    }

    @Override
    public FragmentAction createFragmentAction() {
        return new StatusFragmentAction(this);
    }

    @Override
    public void addFragmentListener(FragmentAction fe) {

    }

    @Override
    public void onPidSetpointChanged(int id, float setpoint) {

    }

    @Override
    public void onSystemCpuUsageChanged(float cpuUsage) {

    }

    @Override
    public void onSystemFreeHeapChanged(int freeHeap) {

    }
}
