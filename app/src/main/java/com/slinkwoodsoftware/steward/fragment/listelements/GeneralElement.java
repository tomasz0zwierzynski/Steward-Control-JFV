package com.slinkwoodsoftware.steward.fragment.listelements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class GeneralElement {

    protected TextView textview;

    protected String tvText;
    protected String name;

    public abstract void setName(String _name);
    public abstract void setText(String _text);
    public abstract String getName();
    public abstract String getText();

    public abstract View getView(LayoutInflater li, ViewGroup vg);
}
