package com.slinkwoodsoftware.steward.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.slinkwoodsoftware.steward.R;
import com.slinkwoodsoftware.steward.fragment.listelements.ButtonElement;
import com.slinkwoodsoftware.steward.fragment.listelements.GeneralElement;
import com.slinkwoodsoftware.steward.fragment.listelements.MinmaxElement;
import com.slinkwoodsoftware.steward.fragment.listelements.TitleElement;
import com.slinkwoodsoftware.steward.fragment.listelements.ToggleElement;
import com.slinkwoodsoftware.steward.fragment.listelements.TwolineElement;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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


    private class SettingsAdapter extends BaseAdapter {

        private List<GeneralElement> elements;

        public SettingsAdapter(){
            //Creating whole list here, because it is static
            //listLength = elementNames.length;
            elements = new ArrayList<GeneralElement>();
            elements.add(new TitleElement("btTitle",getString(R.string.settings_TV_btTitle)));
            elements.add(new ToggleElement("btToggle",getString(R.string.settings_TV_btToggleTextOFF)));
            elements.add(new TitleElement("invTitle",getString(R.string.settings_TV_invTitle)));
            elements.add(new TwolineElement("invNote",getString(R.string.settings_TV_invTip1),getString(R.string.settings_TV_invTip2)));
            elements.add(new MinmaxElement("invX",getString(R.string.settings_TV_invX)));
            elements.add(new MinmaxElement("invY",getString(R.string.settings_TV_invY)));
            elements.add(new MinmaxElement("invZ",getString(R.string.settings_TV_invZ)));
            elements.add(new MinmaxElement("invA",getString(R.string.settings_TV_invA)));
            elements.add(new MinmaxElement("invB",getString(R.string.settings_TV_invB)));
            elements.add(new MinmaxElement("invC",getString(R.string.settings_TV_invC)));
            elements.add(new ButtonElement("invButton",getString(R.string.settings_TV_invButton),getString(R.string.settings_Buton_invButton)));
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

    }
}
