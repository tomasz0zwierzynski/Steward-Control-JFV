package com.oldwoodsoftware.steward.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.DebugEvents;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;
import com.oldwoodsoftware.steward.model.responsibility.listener.DebugFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class DebugFragment extends GeneralFragment {

    List<DebugFragmentListener> debugFragmentListeners = new ArrayList<DebugFragmentListener>();

    Button button;
    EditText editTextCommand;
    EditText editTextValue;
    TextView textTip1;
    TextView textTip2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.debug_fragment, container, false);

        //To remove later
        //debugFragmentListeners.add((DebugFragmentListener) getActivity());

        button = (Button) view.findViewById(R.id.debug_send_button);
        editTextCommand = (EditText) view.findViewById(R.id.debug_ET1);
        textTip1 = (TextView) view.findViewById(R.id.debug_Text1);
        textTip1.setText(R.string.debug_TV_tip1);
        editTextValue = (EditText) view.findViewById(R.id.debug_ET2);
        textTip2 = (TextView) view.findViewById(R.id.debug_Text2);
        textTip2.setText(R.string.debug_TV_tip2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command_to_send = editTextCommand.getText() + "=" + editTextValue.getText();
                for(DebugFragmentListener dfl : debugFragmentListeners){
                    dfl.onDebugCommand(command_to_send);
                }
            }
        });

        return view;
    }

    @Override
    public String toString(){
        return "Debug";
    }

    @Override
    public FragmentEvent createFragmentEvent(PlatformContext context){
        return new DebugEvents(this, context);
    }

    @Override
    public void addFragmentListener(FragmentEvent fe) {
        try {
            debugFragmentListeners.add((DebugFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

}
