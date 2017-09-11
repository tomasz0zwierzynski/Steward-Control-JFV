package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slinkwoodsoftware.steward.R;

public class MinmaxElement extends GeneralElement {

    public MinmaxElement(String _name, String _text){
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
        View view = li.inflate(R.layout.listelement_minmax_edit,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_minmax_edit_TextView);
        textview.setText(tvText);

        return view;
    }


}
