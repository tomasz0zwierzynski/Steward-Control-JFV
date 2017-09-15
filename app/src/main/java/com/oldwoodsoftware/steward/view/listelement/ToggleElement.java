package com.oldwoodsoftware.steward.view.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.responsibility.patron.TogglePatron;

public class ToggleElement extends GeneralElement {
    private boolean initial_button_state;

    private ToggleButton button;

    private boolean buttonState;
    private TogglePatron parent;

    public ToggleElement(TogglePatron _parent,boolean _initial_button_state ,String _name, String _text){
        name = _name;
        tvText = _text;
        parent = _parent;
        initial_button_state = _initial_button_state;
    }

    public String getName(){
        return name;
    }

    public String getText(){
        return tvText;
    }

    public void setName(String _name){
        name = _name;
    }

    public void setText(String _text){
        tvText = _text;
    }

    public boolean getButtonState(){
        return buttonState;
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
                parent.onButtonToggled(ToggleElement.this);
            }
        });

        return view;
    }

}
