package com.oldwoodsoftware.steward.fragment.base;


import android.support.v4.app.Fragment;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;

public abstract class GeneralFragment extends Fragment {

    abstract public FragmentAction createFragmentAction();
    abstract public void addFragmentListener(FragmentAction fe);
}
