package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slinkwoodsoftware.steward.R;

public class TwolineElement extends GeneralElement {

    private TextView textview2;
    private String tvText2;


    public TwolineElement(String _name, String _text, String _text2){
        name = _name;
        tvText = _text;
        tvText2 = _text2;
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
        View view = li.inflate(R.layout.listelement_text_twoline,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_text_twoline_TextView);
        textview.setText(tvText);
        textview2 = (TextView) view.findViewById(R.id.listelement_text_twoline_TextView2);
        textview2.setText(tvText2);

        return view;
    }

}
