package com.oldwoodsoftware.steward;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oldwoodsoftware.steward.fragment.FragmentApplication;
import com.oldwoodsoftware.steward.platform.PlatformContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PlatformContext platformContext;
    private FragmentApplication fragmentApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ApplicationBuild","MainActivity.onCreate() called");

        platformContext = new PlatformContext();
        platformContext.init();

        fragmentApplication = new FragmentApplication(this);
        Log.i("ApplicationBuild","Application build succeed.");
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
