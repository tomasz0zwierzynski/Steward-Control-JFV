package com.oldwoodsoftware.steward.view.fragment;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldwoodsoftware.steward.MainActivity;
import com.oldwoodsoftware.steward.R;
import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;
import com.oldwoodsoftware.steward.model.event.TargetEvents;
import com.oldwoodsoftware.steward.model.responsibility.listener.TargetFragmentListener;
import com.oldwoodsoftware.steward.view.PanelView;

import java.util.ArrayList;
import java.util.List;

public class TargetFragment extends GeneralFragment {
    private TextView textview1;
    private TextView textview2;
    private PanelView panelview;

    private Activity parentActivity;
    private List<TargetFragmentListener> targetListeners = new ArrayList<TargetFragmentListener>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = getActivity();
        //targetListeners.add( (TargetFragmentListener) parentActivity );

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
       /* panelview.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch(action){
                    case (DragEvent.ACTION_DRAG_ENTERED):

                        break;
                    case (DragEvent.ACTION_DRAG_LOCATION):

                        break;
                    case (DragEvent.ACTION_DRAG_EXITED):

                        break;
                    default:
                        break;
                }
                int x = (int)event.getX();
                int y = (int)event.getY();
                if (panelview.isInRect(x,y)){
                    ((PanelView) v).setTargetPosition((int)event.getX(),(int)event.getY());
                    TargetFragment.this.onTargetPositionChanged((int)event.getX(),(int)event.getY());
                }
                return false;
            }
        });*/

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

    public FragmentEvent createFragmentEvent(PlatformContext context){
        return new TargetEvents(this,context);
    }

    @Override
    public void addFragmentListener(FragmentEvent fe) {
        try {
            targetListeners.add((TargetFragmentListener) fe);
        }catch (ClassCastException ex){}
    }

}
