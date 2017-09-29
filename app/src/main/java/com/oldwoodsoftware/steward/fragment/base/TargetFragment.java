package com.oldwoodsoftware.steward.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.TargetFragmentAction;
import com.oldwoodsoftware.steward.fragment.base_listener.TargetFragmentListener;
import com.oldwoodsoftware.steward.old_view.PanelView;

import java.util.ArrayList;
import java.util.List;

public class TargetFragment extends GeneralFragment {
    private List<TargetFragmentListener> targetListeners = new ArrayList<TargetFragmentListener>();

    private TextView textview1;
    private TextView textview2;
    private PanelView panelview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.target, container, false);
        textview1 = (TextView) view.findViewById(R.id.target_TextView1);
        textview1.setText(getString(R.string.target_TV_tip1));
        textview2 = (TextView) view.findViewById(R.id.target_TextView2);
        textview2.setText(getString(R.string.target_TV_tip2));
        panelview = (PanelView) view.findViewById(R.id.target_panelview);
            float ratio = 1.41f;
        for (TargetFragmentListener tfl : targetListeners){
            ratio = tfl.getPanelLenghtRatio();
        }
        panelview.setPanelRatio(ratio);

        panelview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                if (panelview.isInRect(x,y)){
                    ((PanelView) v).setTargetPosition((int)event.getX(),(int)event.getY());
                    TargetFragment.this.onTargetPositionChanged((int)event.getX(),(int)event.getY());
                }
                return true;
            }
        });

        return view;
    }

    public void onTargetPositionChanged(int x_pixels, int y_pixels){
    //TODO: Perform calculation from pixels to percent
        float width = panelview.getRightEdge() - panelview.getLeftEdge();
        float height = panelview.getBottomEdge() - panelview.getTopEdge();

        float x_percent = (100/width)*((float)x_pixels - (float)panelview.getLeftEdge());
        float y_percent = (100/height)*((float)y_pixels - (float)panelview.getTopEdge());

        for (TargetFragmentListener tfl : targetListeners) {
            tfl.onNewTargetPosition(x_percent, y_percent);
        }
    }

    public void onCurrentBallPositionChanged(float x_percent, float y_percent, boolean detected){
        if(detected){
            float width = panelview.getRightEdge() - panelview.getLeftEdge();
            float height = panelview.getBottomEdge() - panelview.getTopEdge();

            int x_pixels = (int) (((x_percent*width)/100) + panelview.getLeftEdge());
            int y_pixels = (int) (((y_percent*height)/100) + panelview.getTopEdge());

            panelview.setBallPosition(x_pixels,y_pixels);
        }else{
            panelview.setBallPosition(100000,100000);
        }
    }

    @Override
    public String toString(){
        return "Target";
    }

    public FragmentAction createFragmentAction(){
        return new TargetFragmentAction(this);
    }

    @Override
    public void addFragmentListener(FragmentAction fe) {
        try {
            targetListeners.add((TargetFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

}
