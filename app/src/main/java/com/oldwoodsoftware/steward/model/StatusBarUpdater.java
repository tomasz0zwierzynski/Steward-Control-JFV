package com.oldwoodsoftware.steward.model;

import android.widget.TextView;

import com.oldwoodsoftware.steward.MainActivity;
import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.bluetooth.BluetoothStatus;

public class StatusBarUpdater {
    private TextView btStatusText;
    private TextView ballStatusText;
    private TextView[] platformStatusText;

    private MainActivity parentActivity;

    public StatusBarUpdater(MainActivity ma){
        btStatusText = (TextView) ma.findViewById(R.id.btStatusText);
        ballStatusText = (TextView) ma.findViewById(R.id.ballTextView);

        platformStatusText = new TextView[6];
        platformStatusText[0] = (TextView) ma.findViewById(R.id.platformXtext); //X
        platformStatusText[1] = (TextView) ma.findViewById(R.id.platformYtext); //Y
        platformStatusText[2] = (TextView) ma.findViewById(R.id.platformZtext); //Z
        platformStatusText[3] = (TextView) ma.findViewById(R.id.platformAtext); //A
        platformStatusText[4] = (TextView) ma.findViewById(R.id.platformBtext); //B
        platformStatusText[5] = (TextView) ma.findViewById(R.id.platformCtext); //C

        parentActivity = ma;
    }

    public void updatePlatfromStatus(int slot, String msg){
        platformStatusText[slot].setText(msg);
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
            ballStatusText.setText(parentActivity.getString(R.string.ballDetected) + " X: " + String.valueOf(posX) + " Y: " + String.valueOf(posY));
        }else{
            ballStatusText.setText(R.string.ballUndetected);
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
            default:
                btStatusText.setText("");
        }
    }

    public void updateBluetoothStatus(String msg){
        btStatusText.setText(msg);
    }

}
