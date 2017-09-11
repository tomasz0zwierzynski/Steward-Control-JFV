package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slinkwoodsoftware.steward.R;

public class TitleElement  extends GeneralElement {

    public TitleElement(String _name, String _text){
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
        View view = li.inflate(R.layout.listelement_text_title,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_text_title_TextView);
        textview.setText(tvText);

        return view;
    }

}
