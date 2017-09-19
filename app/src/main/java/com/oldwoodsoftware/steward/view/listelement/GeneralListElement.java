package com.oldwoodsoftware.steward.view.listelement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class GeneralListElement {
    protected TextView textview;

    protected String tvText;
    protected String name;

    public abstract String getName();
    public abstract void setText(String _text);

    public abstract View getView(LayoutInflater li, ViewGroup vg);
}
