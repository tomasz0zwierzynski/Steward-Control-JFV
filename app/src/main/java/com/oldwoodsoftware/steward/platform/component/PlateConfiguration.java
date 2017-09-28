package com.oldwoodsoftware.steward.platform.component;

import com.oldwoodsoftware.steward.platform.event.PlateEventListener;
import com.oldwoodsoftware.steward.platform.type.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nails on 28.09.2017.
 */

public class PlateConfiguration {
    private Configuration configuration = new Configuration(0,0,0,0,0,0);
    private List<PlateEventListener> plateEventListeners = new ArrayList<>();

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration.getCopy(); //Copy to ensure that configuration is not modified from outside without calling setFunc
        emitConfigurationChanged();
    }

    public void setX(float x){
        configuration.x = x;
        emitConfigurationChanged();
    }

    public void setY(float y){
        configuration.y = y;
        emitConfigurationChanged();
    }

    public void setZ(float z){
        configuration.z = z;
        emitConfigurationChanged();
    }

    public void setRoll(float roll){
        configuration.roll = roll;
        emitConfigurationChanged();
    }

    public void setPitch(float pitch){
        configuration.pitch = pitch;
        emitConfigurationChanged();
    }

    public void setYaw(float yaw){
        configuration.yaw = yaw;
        emitConfigurationChanged();
    }

    public Configuration getConfiguration(){
        return configuration.getCopy();
    }

    public void addPlateEventListener(PlateEventListener pel){
        plateEventListeners.add(pel);
    }

    private void emitConfigurationChanged(){
        for (PlateEventListener pel : plateEventListeners){
            pel.onPlateConfigurationChanged(configuration.getCopy());
        }
    }

}
