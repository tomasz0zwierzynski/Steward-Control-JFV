package com.oldwoodsoftware.steward.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.model.responsibility.patron.ButtonPatron;
import com.oldwoodsoftware.steward.model.responsibility.patron.SliderPatron;
import com.oldwoodsoftware.steward.view.listelement.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InverseFragment extends Fragment {

    private Activity parentActivity;
    private InverseFragmentSliderListener sliderListener;

    private InverseFragment.InverseAdapter inverseAdapter;

    public InverseAdapter getInverseAdapter() {
        return inverseAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = getActivity();
        sliderListener = (InverseFragmentSliderListener) parentActivity;

        View view = inflater.inflate(R.layout.inverse, container, false);

        final ListView listview = (ListView) view.findViewById(R.id.inverse_listview);
        inverseAdapter = new InverseFragment.InverseAdapter();
        listview.setAdapter(inverseAdapter);

        return view;
    }

    @Override
    public String toString(){
        return "Inverse";
    }

    public class InverseAdapter extends BaseAdapter implements SliderPatron, ButtonPatron{

        private List<GeneralElement> elements;

        private int[] sliderValues = new int[6];
        private List<SliderElement> sliderObjects = new ArrayList<SliderElement>();


        public InverseAdapter(){
            for (int i=0;i<6;i++){
                sliderValues[i] = 500;
            }

            //Creating whole list here, because it is static
            //listLength = elementNames.length;
            elements = new ArrayList<GeneralElement>();
            elements.add(new TwolineElement("invTitle",getString(R.string.inverse_TV_tip1),getString(R.string.inverse_TV_tip2)));

            SliderElement se = new SliderElement(this,"invX",getString(R.string.inverse_TV_X),"0 [mm]");
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderElement(this,"invY",getString(R.string.inverse_TV_Y),"0 [mm]");
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderElement(this,"invZ",getString(R.string.inverse_TV_Z),"0 [mm]");
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderElement(this,"invA",getString(R.string.inverse_TV_A),"0 [deg]");
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderElement(this,"invB",getString(R.string.inverse_TV_B),"0 [deg]");
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderElement(this,"invC",getString(R.string.inverse_TV_C),"0 [deg]");
            elements.add(se);
            sliderObjects.add(se);

            ButtonElement be = new ButtonElement(this,"invButton",getString(R.string.inverse_TV_button),getString(R.string.inverse_Buton_invButton));
            elements.add(be);
            //elements.add(new )
        }

        @Override
        public void onSliderProgressChanged(SliderElement sender) {
            int index = sliderObjects.indexOf(sender);
            sliderValues[index] = sender.getProgress();

            InverseFragment.this.sliderListener.onInverseFragmentSliderChange(sliderValues);
        }

        @Override
        public void setSliderText(String[] texts) {
            for(int i=0;i<sliderObjects.size();i++){
                sliderObjects.get(i).setSeekbarDescription(texts[i]);
            }
        }

        public int getCount() {
            return elements.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater =  (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = elements.get(position).getView(inflater,parent);

            return row;
        }

        @Override
        public void onButtonPressed(ButtonElement sender) {
            for (SliderElement se : sliderObjects){
                se.setSeekbarProgress(500);
            }
        }

        @Override
        public void setButtonText(String[] texts) {

        }
    }


}
