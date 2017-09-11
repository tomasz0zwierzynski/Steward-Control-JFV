package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.slinkwoodsoftware.steward.R;

import java.lang.reflect.Method;

public class ToggleElement extends GeneralElement {

    private ToggleButton button;

    public ToggleElement(String _name, String _text){
        name = _name;
        tvText = _text;
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

    public View getView(LayoutInflater li, ViewGroup vg){
        View view = li.inflate(R.layout.listelement_toggle,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_toggle_TextView);
        textview.setText(tvText);
        button = (ToggleButton) view.findViewById(R.id.listelement_toggle_ToggleButton);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //blah blah

            }
        });

        return view;
    }

}
