package com.oldwoodsoftware.steward.view.fragment;


import android.support.v4.app.Fragment;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.event.FragmentEvents;

public abstract class GeneralFragment extends Fragment {

    abstract public FragmentEvents createFragmentEvent(PlatformContext context);
    abstract public void addFragmentListener(FragmentEvents fe);
}
