package com.slinkwoodsoftware.steward.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.slinkwoodsoftware.steward.R;
import com.slinkwoodsoftware.steward.fragment.listener.AccelerometerFragmentStateListener;

public class AccelerometerFragment extends Fragment{
    private TextView textview1;
    private TextView textview2;
    private Button button;
    private TextView textPitch;
    private TextView textRoll;

    private boolean active = false;

    private Activity parentActivity;
    private AccelerometerFragmentStateListener stateListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = getActivity();
        stateListener = (AccelerometerFragmentStateListener) parentActivity;

        View view = inflater.inflate(R.layout.accelerometer, container, false);
        textview1 = (TextView) view.findViewById(R.id.accelerometer_TextView1);
        textview1.setText(getString(R.string.accelerometer_TV_tip1));
        textview2 = (TextView) view.findViewById(R.id.accelerometer_textview2);
        textview2.setText(getString(R.string.accelerometer_TV_tip2));
        textPitch = (TextView) view.findViewById(R.id.accelerometer_pitch);
        textPitch.setText(getString(R.string.accelerometer_TV_pitch));
        textRoll = (TextView) view.findViewById(R.id.accelerometer_roll);
        textRoll.setText(getString(R.string.accelerometer_TV_roll));

        button = (Button) view.findViewById(R.id.accelerometer_button);
        button.setText(getString(R.string.accelerometer_whenOFF_button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (active){
                    changeState(false);
                }else{
                    changeState(true);
                }
            }
        });

        return view;
    }

    public void changeState(boolean toState){
        if (toState){ //changing to active
            active = toState;
            textview2.setText(getString(R.string.accelerometer_TV_tip2b));
            button.setText(getString(R.string.accelerometer_whenON_button));
            //register listener

        }else{ //changing to inactive
            active = toState;
            textview2.setText(getString(R.string.accelerometer_TV_tip2));
            textPitch.setText(getString(R.string.accelerometer_TV_pitch));
            textRoll.setText(getString(R.string.accelerometer_TV_roll));
            button.setText(getString(R.string.accelerometer_whenOFF_button));
        }
        stateListener.onAccelerometerFragmentStateChange(toState);
    }

    public void updateControls(float nPitch, float nRoll){
        textPitch.setText(getString(R.string.accelerometer_TV_pitch) + String.valueOf(nPitch) + " [deg]");
        textRoll.setText(getString(R.string.accelerometer_TV_roll) + String.valueOf(nRoll) + " [deg]");
    }

    @Override
    public String toString(){
        return "Accelerometer";
    }

}
