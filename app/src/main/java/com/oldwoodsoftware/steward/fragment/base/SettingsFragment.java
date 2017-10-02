package com.oldwoodsoftware.steward.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.SettingsFragmentAction;
import com.oldwoodsoftware.steward.fragment.base_listener.SettingsFragmentListener;
import com.oldwoodsoftware.steward.fragment.gui.listelement.ButtonListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.GeneralListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.MinmaxListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.SliderListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.TitleListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.ToggleListElement;
import com.oldwoodsoftware.steward.fragment.gui.listelement.TwolineListElement;
import com.oldwoodsoftware.steward.fragment.gui.listpatron.ButtonPatron;
import com.oldwoodsoftware.steward.fragment.gui.listpatron.SliderPatron;
import com.oldwoodsoftware.steward.fragment.gui.listpatron.TogglePatron;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends GeneralFragment {
    private List<SettingsFragmentListener> settingsListeners = new ArrayList<SettingsFragmentListener>();

    private boolean isBTconnected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        for (SettingsFragmentListener sfl : settingsListeners){
            isBTconnected = sfl.isBluetoothConnected();
        }

        View view = inflater.inflate(R.layout.settings, container, false);
        final ListView listview = (ListView) view.findViewById(R.id.settings_listview);
        SettingsListAdapter sa = new SettingsListAdapter();
        listview.setAdapter(sa);

        return view;
    }

    @Override
    public FragmentAction createFragmentAction(){
        return new SettingsFragmentAction(this);
    }

    @Override
    public void addFragmentListener(FragmentAction fe) {
        try {
            settingsListeners.add((SettingsFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

    @Override
    public String toString(){
        return "Settings";
    }

    private class SettingsListAdapter extends BaseAdapter implements SliderPatron, ButtonPatron, TogglePatron {
        private List<GeneralListElement> elements;
        private List<MinmaxListElement> minmaxElements;

        public SettingsListAdapter(){
            elements = new ArrayList<GeneralListElement>();
            minmaxElements = new ArrayList<MinmaxListElement>();
            elements.add(new TitleListElement("btTitle",getString(R.string.settings_TV_btTitle)));
            elements.add(new ToggleListElement(this,SettingsFragment.this.isBTconnected,"btToggle",getString(R.string.settings_TV_btToggleTextOFF)));
            elements.add(new TitleListElement("invTitle",getString(R.string.settings_TV_invTitle)));
            elements.add(new TwolineListElement("invNote",getString(R.string.settings_TV_invTip1),getString(R.string.settings_TV_invTip2)));
            MinmaxListElement minmax = new MinmaxListElement("invX",getString(R.string.settings_TV_invX));
            minmaxElements.add(minmax);
            elements.add(minmax);
            minmax = new MinmaxListElement("invY",getString(R.string.settings_TV_invY));
            minmaxElements.add(minmax);
            elements.add(minmax);
            minmax = new MinmaxListElement("invZ",getString(R.string.settings_TV_invZ));
            minmaxElements.add(minmax);
            elements.add(minmax);
            minmax = new MinmaxListElement("invA",getString(R.string.settings_TV_invA));
            minmaxElements.add(minmax);
            elements.add(minmax);
            minmax = new MinmaxListElement("invB",getString(R.string.settings_TV_invB));
            elements.add(minmax);
            minmaxElements.add(minmax);
            minmax = new MinmaxListElement("invC",getString(R.string.settings_TV_invC));
            elements.add(minmax);
            minmaxElements.add(minmax);
            elements.add(new ButtonListElement(this,"invButton",getString(R.string.settings_TV_invButton),getString(R.string.settings_Buton_invButton)));
            elements.add(new TitleListElement("accTitle", getString(R.string.settings_TV_accTitle)));
            elements.add(new TwolineListElement("accNote",getString(R.string.settings_TV_accTip1),getString(R.string.settings_TV_accTip2)));
            elements.add(new SliderListElement(this, 500,"accPitchSlider",getString(R.string.settings_TV_accPitch),"0"));
            elements.add(new SliderListElement(this, 500,"accRollSlider",getString(R.string.settings_TV_accRoll),"0"));
            elements.add(new ButtonListElement(this,"accButton",getString(R.string.settings_TV_accButton),getString(R.string.settings_Buton_accButton)));
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
        public void onSliderProgressChanged(SliderListElement sender) {

        }

        @Override
        public void setSliderText(String[] texts) {

        }

        @Override
        public void onButtonPressed(ButtonListElement sender) {

        }

        @Override
        public void onButtonToggled(ToggleListElement sender) {
            //One toggle element for now, so just send it further
            boolean state = sender.getButtonState();

            for (SettingsFragmentListener sfl : settingsListeners) {
                if (state) {
                    sfl.onBluetoothConnectionButtonChecked();
                } else {
                    sfl.onBluetoothConnectionButtonUnchecked();
                }
            }
        }

        @Override
        public void setButtonText(String text) {

        }
    }

}
