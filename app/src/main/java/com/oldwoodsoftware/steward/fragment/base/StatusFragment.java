package com.oldwoodsoftware.steward.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.core.bluetooth.BluetoothState;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.StatusFragmentAction;
import com.oldwoodsoftware.steward.fragment.base_listener.StatusFragmentListener;

import java.util.ArrayList;
import java.util.List;

import static com.oldwoodsoftware.steward.old_model.bluetooth.BluetoothStatus.Connected;

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
        System.out.println("Debug: updateFreeHeap()");
        freeHeap.setText(" " + String.valueOf(value) + " bytes");
    }

    public void updateCPUusage(float value){
        System.out.println("Debug: updateCpuUsage()");
        cpuUsage.setText(" " + String.valueOf(value) + " %");
    }

    public void updateBluetoothStatus(BluetoothState bs) {
        System.out.println("Debug: updateBluetoothState(): " +  bs.toString());
        switch(bs){

            case undefined:
                break;
            case disconnected:
                btStatusText.setText(R.string.btGettingOff);
                break;
            case connecting:
                btStatusText.setText(R.string.btGettingConnected);
                break;
            case connected:
                btStatusText.setText(R.string.btConnected);
                break;
            case disconnecting:
                btStatusText.setText(R.string.btDisconnected);
                break;
            case error:
                btStatusText.setText(R.string.btErrorOccured);
                break;
        }
    }

    public void updateBluetoothMessage(String msg){
        btMessageText.setText(msg);
    }

    @Override
    public FragmentAction createFragmentAction() {
        System.out.println("Debug: Create Fragment action()");
        return new StatusFragmentAction(this);
    }

    @Override
    public void addFragmentListener(FragmentAction fe) {
        System.out.println("Debug: Add fragment listener()");
        try {
            statusListeners.add((StatusFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

}
