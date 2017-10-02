package com.oldwoodsoftware.steward;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        platformContext = new PlatformContext();
        platformContext.init();

        fragmentApplication = new FragmentApplication(this);

        //Debug Purposes
        System.out.println(platformContext.getBallOnPlate().toString());
        System.out.println(platformContext.getStateMachine().toString());
        System.out.println(platformContext.getGeneralSystem().toString());
        System.out.println(platformContext.getPlateConfiguration().toString());
        System.out.println(platformContext.getPidControlX().toString());
        System.out.println(platformContext.getPidControlY().toString());
        System.out.println(platformContext.getBluetoothConnection().toString());
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
