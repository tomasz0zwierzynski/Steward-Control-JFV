package com.slinkwoodsoftware.steward.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.slinkwoodsoftware.steward.R;
import com.slinkwoodsoftware.steward.fragment.listelements.ButtonElement;
import com.slinkwoodsoftware.steward.fragment.listelements.GeneralElement;
import com.slinkwoodsoftware.steward.fragment.listelements.SliderElement;
import com.slinkwoodsoftware.steward.fragment.listelements.TwolineElement;

import java.util.ArrayList;
import java.util.List;

public class InverseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inverse, container, false);

        final ListView listview = (ListView) view.findViewById(R.id.inverse_listview);
        InverseFragment.InverseAdapter sa = new InverseFragment.InverseAdapter();
        listview.setAdapter(sa);

        return view;
    }

    @Override
    public String toString(){
        return "Inverse";
    }

    private class InverseAdapter extends BaseAdapter {

        private List<GeneralElement> elements;

        public InverseAdapter(){
            //Creating whole list here, because it is static
            //listLength = elementNames.length;
            elements = new ArrayList<GeneralElement>();
            elements.add(new TwolineElement("invTitle",getString(R.string.inverse_TV_tip1),getString(R.string.inverse_TV_tip2)));
            elements.add(new SliderElement("invX",getString(R.string.inverse_TV_X),"0 [mm]"));
            elements.add(new SliderElement("invY",getString(R.string.inverse_TV_Y),"0 [mm]"));
            elements.add(new SliderElement("invZ",getString(R.string.inverse_TV_Z),"0 [mm]"));
            elements.add(new SliderElement("invA",getString(R.string.inverse_TV_A),"0 [deg]"));
            elements.add(new SliderElement("invB",getString(R.string.inverse_TV_B),"0 [deg]"));
            elements.add(new SliderElement("invC",getString(R.string.inverse_TV_C),"0 [deg]"));
            elements.add(new ButtonElement("invButton",getString(R.string.inverse_TV_button),getString(R.string.inverse_Buton_invButton)));
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
