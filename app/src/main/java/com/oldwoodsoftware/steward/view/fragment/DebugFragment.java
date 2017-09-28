package com.oldwoodsoftware.steward.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.event.DebugFragmentEvents;
import com.oldwoodsoftware.steward.model.event.FragmentEvents;
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

    TextView consoleView;
    ScrollView debugScroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.debug_fragment, container, false);

        button = (Button) view.findViewById(R.id.debug_send_button);
        editTextCommand = (EditText) view.findViewById(R.id.debug_ET1);
        textTip1 = (TextView) view.findViewById(R.id.debug_Text1);
        textTip1.setText(R.string.debug_TV_tip1);
        editTextValue = (EditText) view.findViewById(R.id.debug_ET2);
        textTip2 = (TextView) view.findViewById(R.id.debug_Text2);
        textTip2.setText(R.string.debug_TV_tip2);

        consoleView = (TextView)  view.findViewById(R.id.debugLog);
        debugScroll = (ScrollView) view.findViewById(R.id.debug_scroll);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command_to_send = editTextCommand.getText() + "=" + editTextValue.getText();
                for(DebugFragmentListener dfl : debugFragmentListeners){
                    dfl.onDebugCommand(command_to_send);
                }
            }
        });


        debugScroll.postDelayed(new Runnable() {
            @Override
            public void run() {
                debugScroll.fullScroll(ScrollView.FOCUS_DOWN);
                //debugScroll.invalidate();
            }
        },1000);

        return view;
    }

    public void println(String str){
        //TODO: clean this code by finding way to remove old messages from Console or add slidingView
        String old = consoleView.getText().toString();
        String newString = old + '\n' + str;
        int len = newString.length();
        String newest_string = newString;
        if (len > 200){
            int chars_to_remove = len - 200;
            newest_string = newString.substring(chars_to_remove);
        }
        consoleView.setText(newest_string);
    }

    @Override
    public FragmentEvents createFragmentEvent(PlatformContext context){
        return new DebugFragmentEvents(this, context);
    }

    @Override
    public void addFragmentListener(FragmentEvents fe) {
        try {
            debugFragmentListeners.add((DebugFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

    @Override
    public String toString(){
        return "Debug";
    }


}
