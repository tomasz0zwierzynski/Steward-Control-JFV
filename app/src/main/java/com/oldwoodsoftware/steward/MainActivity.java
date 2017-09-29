package com.oldwoodsoftware.steward;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.oldwoodsoftware.steward.fragment.FragmentApplication;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.fragment.FragmentActionManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PlatformContext platformContext;
    private FragmentApplication fragmentApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        platformContext = new PlatformContext();
        platformContext.init();

        fragmentApplication = new FragmentApplication(this);
    }

    public PlatformContext getPlatformContext(){
        return platformContext;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
