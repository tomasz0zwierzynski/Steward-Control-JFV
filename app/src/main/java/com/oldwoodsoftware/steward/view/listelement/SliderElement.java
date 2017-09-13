package com.oldwoodsoftware.steward.view.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.responsibility.patron.SliderPatron;

public class SliderElement extends GeneralElement{

    private TextView textview2;
    private String tvText2;
    private SeekBar seekbar;

    private int iProgress;
    private SliderPatron parent;

    public SliderElement(SliderPatron _parent, String _name, String _text, String _text2){
        parent = _parent;

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

    public void setSeekbarProgress(int prog){
        seekbar.setProgress(prog);
    }

    public void setSeekbarDescription(String _text){
        tvText2 = _text;
        textview2.setText(tvText2);
    }

    public int getProgress(){
        return iProgress;
    }


    public View getView(LayoutInflater li, ViewGroup vg){
        View view = li.inflate(R.layout.listelement_slider,vg,false);
        textview = (TextView) view.findViewById(R.id.listelement_slider_TextView);
        textview.setText(tvText);
        textview2 = (TextView) view.findViewById(R.id.listelement_slider_Textview2);
        textview2.setText(tvText2);
        seekbar = (SeekBar) view.findViewById(R.id.listelement_slider_seekbar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iProgress = progress;
                parent.onSliderProgressChanged(SliderElement.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }
}
