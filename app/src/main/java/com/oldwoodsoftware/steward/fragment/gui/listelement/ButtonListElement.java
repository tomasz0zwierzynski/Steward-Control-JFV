package com.oldwoodsoftware.steward.fragment.gui.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.gui.listpatron.ButtonPatron;

public class ButtonListElement extends GeneralListElement {

    private Button button;
    private String buttonText;

    private ButtonPatron parent;

    public ButtonListElement(ButtonPatron _parent, String _name, String _text, String _buttonText){
        name = _name;
        tvText = _text;
        buttonText = _buttonText;
        parent = _parent;
    }

    public String getName(){
        return name;
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
                parent.onButtonPressed(ButtonListElement.this);
            }
        });

        return view;
    }

}
