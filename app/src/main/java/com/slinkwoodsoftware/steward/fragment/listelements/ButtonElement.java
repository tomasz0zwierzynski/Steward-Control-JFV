package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.slinkwoodsoftware.steward.R;

public class ButtonElement extends GeneralElement {

    private Button button;
    private String buttonText;

    public ButtonElement(String _name, String _text, String _buttonText){
        name = _name;
        tvText = _text;
        buttonText = _buttonText;
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
        View view = li.inflate(R.layout.listelement_button,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_button_TextView);
        textview.setText(tvText);
        button = (Button) view.findViewById(R.id.listelement_button_Button);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
