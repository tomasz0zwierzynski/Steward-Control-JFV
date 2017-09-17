package com.oldwoodsoftware.steward.view.fragment;


import android.support.v4.app.Fragment;

import com.oldwoodsoftware.steward.model.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvent;

public abstract class GeneralFragment extends Fragment {

    abstract public FragmentEvent createFragmentEvent(PlatformContext context);
    abstract public void addFragmentListener(FragmentEvent fe);
}
