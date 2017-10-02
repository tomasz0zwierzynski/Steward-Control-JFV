package com.oldwoodsoftware.steward.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.StatusFragmentAction;
import com.oldwoodsoftware.steward.fragment.base_listener.StatusFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends GeneralFragment{
    private List<StatusFragmentListener> statusListeners = new ArrayList<StatusFragmentListener>();

    private TextView btStatusText;
    private TextView btMessageText;
    private TextView ballStatusText1;
    private TextView ballStatusText2;
    private TextView[] platformStatusText;

    private TextView cpuUsage;
    private TextView freeHeap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.statusbar_fragment, container, false);

        btStatusText = (TextView) view.findViewById(R.id.btStatusText);
        ballStatusText1 = (TextView) view.findViewById(R.id.ballTextView);
        ballStatusText2 = (TextView) view.findViewById(R.id.ballTextView2);
        btMessageText = (TextView) view.findViewById(R.id.btMessageText);
        platformStatusText = new TextView[6];
        platformStatusText[0] = (TextView) view.findViewById(R.id.platformXtext); //X
        platformStatusText[1] = (TextView) view.findViewById(R.id.platformYtext); //Y
        platformStatusText[2] = (TextView) view.findViewById(R.id.platformZtext); //Z
        platformStatusText[3] = (TextView) view.findViewById(R.id.platformAtext); //A
        platformStatusText[4] = (TextView) view.findViewById(R.id.platformBtext); //B
        platformStatusText[5] = (TextView) view.findViewById(R.id.platformCtext); //C

        cpuUsage = (TextView) view.findViewById(R.id.status_freecpu_value);
        freeHeap = (TextView) view.findViewById(R.id.status_freeheap);

        return view;
    }

    public void updateFreeHeap(int value){
        //((TextView) getActivity().findViewById(R.id.status_freecpu_value)).setText(" " + String.valueOf(value) + " bytes");
        freeHeap.setText(" " + String.valueOf(value) + " bytes");
    }

    public void updateCPUusage(float value){
        cpuUsage.setText(" " + String.valueOf(value) + " %");
    }


    @Override
    public FragmentAction createFragmentAction() {
        return new StatusFragmentAction(this);
    }

    @Override
    public void addFragmentListener(FragmentAction fe) {
        try {
            statusListeners.add((StatusFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

}
