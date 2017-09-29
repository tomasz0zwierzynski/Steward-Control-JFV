package com.oldwoodsoftware.steward.old_model;

import android.widget.TextView;

import com.oldwoodsoftware.steward.MainActivity;
import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.old_model.bluetooth.BluetoothStatus;
import com.oldwoodsoftware.steward.old_model.responsibility.listener.BluetoothDataListener;

public class StatusBarUpdater implements BluetoothDataListener{
    private TextView btStatusText;
    private TextView btMessageText;
    private TextView ballStatusText1;
    private TextView ballStatusText2;
    private TextView[] platformStatusText;

    private TextView cpuUsage;
    private TextView freeHeap;

    private MainActivity parentActivity;

    public StatusBarUpdater(MainActivity ma){
        btStatusText = (TextView) ma.findViewById(R.id.btStatusText);
        ballStatusText1 = (TextView) ma.findViewById(R.id.ballTextView);
        ballStatusText2 = (TextView) ma.findViewById(R.id.ballTextView2);
        btMessageText = (TextView) ma.findViewById(R.id.btMessageText);
        platformStatusText = new TextView[6];
        platformStatusText[0] = (TextView) ma.findViewById(R.id.platformXtext); //X
        platformStatusText[1] = (TextView) ma.findViewById(R.id.platformYtext); //Y
        platformStatusText[2] = (TextView) ma.findViewById(R.id.platformZtext); //Z
        platformStatusText[3] = (TextView) ma.findViewById(R.id.platformAtext); //A
        platformStatusText[4] = (TextView) ma.findViewById(R.id.platformBtext); //B
        platformStatusText[5] = (TextView) ma.findViewById(R.id.platformCtext); //C

        cpuUsage = (TextView) ma.findViewById(R.id.status_freecpu_value);
        freeHeap = (TextView) ma.findViewById(R.id.status_freeheap);

        parentActivity = ma;
    }

    public void updateFreeHeap(int value){
        freeHeap.setText(" " + String.valueOf(value) + " bytes");
    }

    public void updateCPUusage(float value){
        cpuUsage.setText(" " + String.valueOf(value) + " %");
    }

    public void updatePlatformStatus(int slot, String msg){
        platformStatusText[slot].setText(msg);
    }

    public void updatePlatformStatus(float pos, int index){
        platformStatusText[index].setText(String.valueOf(pos));
    }

    public void updatePlatformStatus(float[] posvals, boolean show){
        if (show){
            for (int i=0; i<posvals.length; i++){
                platformStatusText[i].setText(String.valueOf(posvals[i]));
            }
        }else{
            for (TextView tv: platformStatusText){
                tv.setText("-");
            }
        }
    }

    public void updateBallStatus(float posX, float posY, boolean detected){
        if (detected){
            ballStatusText1.setText(parentActivity.getString(R.string.ballDetected) + " X= " + String.valueOf(posX));
            ballStatusText2.setText("Y= " + String.valueOf(posY));
        }else{
            ballStatusText1.setText(R.string.ballUndetected);
            ballStatusText1.setText(" ");
        }
    }

    public void updateBallStatus(float pos, boolean is_x_value){
        if (is_x_value){
            ballStatusText1.setText(parentActivity.getString(R.string.ballDetected) + " X: " + String.valueOf(pos));
        }else{
            ballStatusText2.setText("Y= " + String.valueOf(pos));
        }
    }

    public void updateBluetoothStatus(BluetoothStatus bs){
        switch (bs){
            case Off:
                btStatusText.setText(R.string.btOff);
                break;
            case On:
                btStatusText.setText(R.string.btOn);
                break;
            case Connected:
                btStatusText.setText(R.string.btConnected);
                break;
            case Connecting:
                btStatusText.setText(R.string.btGettingConnected);
                break;
            case TurningOff:
                btStatusText.setText(R.string.btGettingOff);
                break;
            case TurningOn:
                btStatusText.setText(R.string.btGettingOn);
                break;
            case Disconnected:
                btStatusText.setText(R.string.btDisconnected);
                break;
            case ErrorOccured:
                btStatusText.setText(R.string.btErrorOccured);
                break;
            default:
                btStatusText.setText("");
        }
    }

    public void updateBluetoothStatus(String msg){
        btStatusText.setText(msg);
    }

    @Override
    public void onBluetoothData(byte[] data) {
        //bytes received...
        String str = String.valueOf(data.length) + " bytes received.";
        btMessageText.setText(str);
    }

    @Override
    public void onBluetoothStateChanged(BluetoothStatus btStat) {
        updateBluetoothStatus(btStat);
    }

    @Override
    public void onBluetoothMessage(String msg) {
        btMessageText.setText(msg);
    }
}