package com.oldwoodsoftware.steward.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.responsibility.listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.model.responsibility.patron.ButtonPatron;
import com.oldwoodsoftware.steward.model.responsibility.patron.SliderPatron;
import com.oldwoodsoftware.steward.model.responsibility.patron.TogglePatron;
import com.oldwoodsoftware.steward.view.listelement.ButtonElement;
import com.oldwoodsoftware.steward.view.listelement.GeneralElement;
import com.oldwoodsoftware.steward.view.listelement.MinmaxElement;
import com.oldwoodsoftware.steward.view.listelement.SliderElement;
import com.oldwoodsoftware.steward.view.listelement.TitleElement;
import com.oldwoodsoftware.steward.view.listelement.ToggleElement;
import com.oldwoodsoftware.steward.view.listelement.TwolineElement;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private Activity parentActivity;
    private SettingsFragmentListener settingsListener;

    private boolean isBTconnected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = getActivity();
        settingsListener = (SettingsFragmentListener) parentActivity;

        isBTconnected = settingsListener.isBluetoothConnected();

        View view = inflater.inflate(R.layout.settings, container, false);
        final ListView listview = (ListView) view.findViewById(R.id.settings_listview);
        SettingsAdapter sa = new SettingsAdapter();
        listview.setAdapter(sa);

        return view;
    }

    @Override
    public String toString(){
        return "Settings";
    }

    private class SettingsAdapter extends BaseAdapter implements SliderPatron, ButtonPatron, TogglePatron{

        private List<GeneralElement> elements;

        public SettingsAdapter(){
            elements = new ArrayList<GeneralElement>();
            elements.add(new TitleElement("btTitle",getString(R.string.settings_TV_btTitle)));
            elements.add(new ToggleElement(this,SettingsFragment.this.isBTconnected,"btToggle",getString(R.string.settings_TV_btToggleTextOFF)));
            elements.add(new TitleElement("invTitle",getString(R.string.settings_TV_invTitle)));
            elements.add(new TwolineElement("invNote",getString(R.string.settings_TV_invTip1),getString(R.string.settings_TV_invTip2)));
            elements.add(new MinmaxElement("invX",getString(R.string.settings_TV_invX)));
            elements.add(new MinmaxElement("invY",getString(R.string.settings_TV_invY)));
            elements.add(new MinmaxElement("invZ",getString(R.string.settings_TV_invZ)));
            elements.add(new MinmaxElement("invA",getString(R.string.settings_TV_invA)));
            elements.add(new MinmaxElement("invB",getString(R.string.settings_TV_invB)));
            elements.add(new MinmaxElement("invC",getString(R.string.settings_TV_invC)));
            elements.add(new ButtonElement(this,"invButton",getString(R.string.settings_TV_invButton),getString(R.string.settings_Buton_invButton)));
            elements.add(new TitleElement("accTitle", getString(R.string.settings_TV_accTitle)));
            elements.add(new TwolineElement("accNote",getString(R.string.settings_TV_accTip1),getString(R.string.settings_TV_accTip2)));
            elements.add(new SliderElement(this, 500,"accPitchSlider",getString(R.string.settings_TV_accPitch),"0"));
            elements.add(new SliderElement(this, 500,"accRollSlider",getString(R.string.settings_TV_accRoll),"0"));
            elements.add(new ButtonElement(this,"accButton",getString(R.string.settings_TV_accButton),getString(R.string.settings_Buton_accButton)));
            //elements.add(new )
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
        public void onSliderProgressChanged(SliderElement sender) {

        }

        @Override
        public void setSliderText(String[] texts) {

        }

        @Override
        public void onButtonPressed(ButtonElement sender) {

        }

        @Override
        public void onButtonToggled(ToggleElement sender) {
            //One toggle element for now, so just send it further
            boolean state = sender.getButtonState();
            if (state){
                settingsListener.onBluetoothConnectionButtonChecked();
            }else{
                settingsListener.onBluetoothConnectionButtonUnchecked();
            }

        }

        @Override
        public void setButtonText(String text) {

        }
    }
}
