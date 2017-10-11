package com.oldwoodsoftware.steward.fragment.gui.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.gui.listpatron.TogglePatron;

public class ToggleListElement extends GeneralListElement {
    private boolean initial_button_state;

    private ToggleButton button;

    private boolean buttonState;
    private TogglePatron parent;

    public ToggleListElement(TogglePatron _parent, boolean _initial_button_state , String _name, String _text){
        name = _name;
        tvText = _text;
        parent = _parent;
        initial_button_state = _initial_button_state;
    }

    public String getName(){
        return name;
    }

    public void setText(String _text){
        tvText = _text;
    }

    public boolean getButtonState(){
        return buttonState;
    }

    public void setButtonState(boolean state){
        button.setChecked(state);
    }

    public View getView(LayoutInflater li, ViewGroup vg){
        View view = li.inflate(R.layout.listelement_toggle,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_toggle_TextView);
        textview.setText(tvText);
        button = (ToggleButton) view.findViewById(R.id.listelement_toggle_ToggleButton);
        button.setChecked(initial_button_state);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonState = isChecked;
                parent.onButtonToggled(ToggleListElement.this);
            }
        });

        return view;
    }

}
