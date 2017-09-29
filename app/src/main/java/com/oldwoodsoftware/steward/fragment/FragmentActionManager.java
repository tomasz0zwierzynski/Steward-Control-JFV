package com.oldwoodsoftware.steward.fragment;

import com.oldwoodsoftware.steward.fragment.action.AccelerometerFragmentAction;
import com.oldwoodsoftware.steward.fragment.action.DebugFragmentAction;
import com.oldwoodsoftware.steward.fragment.action.FragmentAction;
import com.oldwoodsoftware.steward.fragment.action.SettingsFragmentAction;
import com.oldwoodsoftware.steward.fragment.base.AccelerometerFragment;
import com.oldwoodsoftware.steward.fragment.base.GeneralFragment;
import com.oldwoodsoftware.steward.fragment.base.SettingsFragment;
import com.oldwoodsoftware.steward.fragment.gui.StewardFragmentPagerAdapter;
import com.oldwoodsoftware.steward.fragment.type.FragmentType;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.event.BallEventListener;
import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;
import com.oldwoodsoftware.steward.platform.event.PidControlEventListener;
import com.oldwoodsoftware.steward.platform.event.PlateEventListener;
import com.oldwoodsoftware.steward.platform.event.StateMachineEventListener;
import com.oldwoodsoftware.steward.platform.event.SystemEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentActionManager {
    private List<FragmentAction> fragmentActions = new ArrayList<FragmentAction>();

    public FragmentActionManager(GeneralFragment... actionFragments){
        for (GeneralFragment gf : actionFragments){
            FragmentAction fa = gf.createFragmentAction();
            gf.addFragmentListener(fa);
            fragmentActions.add(fa);
        }
    }

    //TODO: at particular fragmentAction getFrom pContext what it needs
    public void activeFragmentsAction(PlatformContext pContext){
        for(FragmentAction fa : fragmentActions){
            fa.activate(pContext);
        }
    }

    public void connectListenersWithPlatform(PlatformContext pContext){
        //Some magic here, anyone has idea if it is "clean" ?
        for (FragmentAction fa : fragmentActions){
            if(fa instanceof BallEventListener){
                pContext.getBallOnPlate().addBallEventListener((BallEventListener)fa);
            }
            if(fa instanceof BluetoothEventListener){
                pContext.getBluetoothConnection().addBluetoothListener((BluetoothEventListener)fa);
            }
            if(fa instanceof PidControlEventListener){
                pContext.getPidControlX().addPidControlEventListener((PidControlEventListener)fa);
                pContext.getPidControlY().addPidControlEventListener((PidControlEventListener)fa);
            }
            if(fa instanceof PlateEventListener){
                pContext.getPlateConfiguration().addPlateEventListener((PlateEventListener)fa);
            }
            if(fa instanceof StateMachineEventListener){
                pContext.getStateMachine().addStateMachineEventListener((StateMachineEventListener)fa);
            }
            if(fa instanceof SystemEventListener){
                pContext.getGeneralSystem().addSystemEventListener((SystemEventListener)fa);
            }
        }
    }

    public FragmentAction getFragmentEvents(int at){
        return fragmentActions.get(at);
    }

}