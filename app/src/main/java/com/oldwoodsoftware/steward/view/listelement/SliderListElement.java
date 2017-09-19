package com.oldwoodsoftware.steward.view.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.responsibility.patron.SliderPatron;

public class SliderListElement extends GeneralListElement {

    private TextView textview2;
    private String tvText2;
    private SeekBar seekbar;

    private int iProgress;
    private SliderPatron parent;

    private int init_progress;

    public SliderListElement(SliderPatron _parent, int _init_progress , String _name, String _text, String _text2){
        parent = _parent;

        name = _name;
        tvText = _text;
        tvText2 = _text2;
        init_progress = _init_progress;
    }

    public String getName(){
        return name;
    }

    public void setText(String _text){
        tvText = _text;
    }

    public void setSeekbarProgress(int prog){
        try {
            seekbar.setProgress(prog);
        }catch(Exception ex){}
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
        seekbar.setProgress(init_progress);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iProgress = progress;
                parent.onSliderProgressChanged(SliderListElement.this);
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
