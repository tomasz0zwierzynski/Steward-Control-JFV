package com.oldwoodsoftware.steward.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvents;
import com.oldwoodsoftware.steward.model.event.InverseFragmentEvents;
import com.oldwoodsoftware.steward.model.responsibility.listener.InverseFragmentSliderListener;
import com.oldwoodsoftware.steward.model.responsibility.patron.ButtonPatron;
import com.oldwoodsoftware.steward.model.responsibility.patron.SliderPatron;
import com.oldwoodsoftware.steward.view.listelement.*;

import java.util.ArrayList;
import java.util.List;

public class InverseFragment extends GeneralFragment {
    private List<InverseFragmentSliderListener> sliderListeners = new ArrayList<InverseFragmentSliderListener>();

    private InverseListAdapter inverseAdapter;

    private int[] initial_progresses = {500,500,500,500,500,500};
    private String[] initial_strings = {" ", " ", " ", " ", " ", " "};

    public InverseListAdapter getInverseAdapter() {
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

        for(InverseFragmentSliderListener ifl : sliderListeners){
            initial_progresses = ifl.getCurrentSliderProgresses();
            initial_strings = ifl.getInverseFragmentSliderTexts();
        }

        View view = inflater.inflate(R.layout.inverse, container, false);

        final ListView listview = (ListView) view.findViewById(R.id.inverse_listview);
        inverseAdapter = new InverseListAdapter();
        listview.setAdapter(inverseAdapter);

        return view;
    }

    @Override
    public FragmentEvents createFragmentEvent(PlatformContext context){
        return new InverseFragmentEvents(this, context);
    }

    @Override
    public void addFragmentListener(FragmentEvents fe) {
        try {
            sliderListeners.add((InverseFragmentSliderListener) fe);
        }catch (ClassCastException ex){}
    }

    @Override
    public String toString(){
        return "Inverse";
    }

    public class InverseListAdapter extends BaseAdapter implements SliderPatron, ButtonPatron{
        private List<GeneralListElement> elements;

        private int[] sliderValues = new int[6];
        private List<SliderListElement> sliderObjects = new ArrayList<SliderListElement>();

        public InverseListAdapter(){
            InverseFragment t = InverseFragment.this;

            for (int i=0;i<6;i++){
                sliderValues[i] = t.initial_progresses[i];
            }

            //Creating whole list here, because it is static
            //listLength = elementNames.length;
            elements = new ArrayList<GeneralListElement>();
            elements.add(new TwolineListElement("invTitle",getString(R.string.inverse_TV_tip1),getString(R.string.inverse_TV_tip2)));

            SliderListElement se = new SliderListElement(this,t.initial_progresses[0],"invX",getString(R.string.inverse_TV_X),t.initial_strings[0]);
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderListElement(this,t.initial_progresses[1],"invY",getString(R.string.inverse_TV_Y),t.initial_strings[1]);
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderListElement(this,t.initial_progresses[2],"invZ",getString(R.string.inverse_TV_Z),t.initial_strings[2]);
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderListElement(this,t.initial_progresses[3],"invA",getString(R.string.inverse_TV_A),t.initial_strings[3]);
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderListElement(this,t.initial_progresses[4],"invB",getString(R.string.inverse_TV_B),t.initial_strings[4]);
            elements.add(se);
            sliderObjects.add(se);
            se = new SliderListElement(this,t.initial_progresses[5],"invC",getString(R.string.inverse_TV_C),t.initial_strings[5]);
            elements.add(se);
            sliderObjects.add(se);

            ButtonListElement be = new ButtonListElement(this,"invButton",getString(R.string.inverse_TV_button),getString(R.string.inverse_Buton_invButton));
            elements.add(be);
            //elements.add(new )
        }

        @Override
        public void onSliderProgressChanged(SliderListElement sender) {
            int index = sliderObjects.indexOf(sender);
            sliderValues[index] = sender.getProgress();

            for(InverseFragmentSliderListener ifl : InverseFragment.this.sliderListeners) {
                ifl.onInverseFragmentSliderChange(sliderValues);
            }
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
        public void onButtonPressed(ButtonListElement sender) {
            for (InverseFragmentSliderListener lis :sliderListeners){
                lis.onZeroButtonPressed();
            }
            for (SliderListElement se : sliderObjects){
                se.setSeekbarProgress(500);
            }
        }

        @Override
        public void setButtonText(String text) {

        }
    }

}
