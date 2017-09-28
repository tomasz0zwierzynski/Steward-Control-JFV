package com.oldwoodsoftware.steward.platform.type;

/**
 * Created by Nails on 28.09.2017.
 */

public class Configuration {
    public float x;
    public float y;
    public float z;
    public float roll;
    public float pitch;
    public float yaw;

    public Configuration(float x, float y, float z, float roll, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Configuration(float[] config) throws IllegalArgumentException{
        if (config.length != 6){
            throw new IllegalArgumentException("Configuration should have 6 components: [X Y Z Roll Pitch Yaw]");
        }

        this.x = config[0];
        this.y = config[1];
        this.z = config[2];
        this.roll = config[3];
        this.pitch = config[4];
        this.yaw = config[5];
    }

    protected Configuration(Configuration configuration){
        this.x = configuration.x;
        this.y = configuration.y;
        this.z = configuration.z;
        this.roll = configuration.roll;
        this.pitch = configuration.pitch;
        this.yaw = configuration.yaw;
    }

    public void set(float x, float y, float z, float roll, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public void set(float[] config) throws IllegalArgumentException{
        if (config.length != 6){
            throw new IllegalArgumentException("Configuration should have 6 components: [X Y Z Roll Pitch Yaw]");
        }

        this.x = config[0];
        this.y = config[1];
        this.z = config[2];
        this.roll = config[3];
        this.pitch = config[4];
        this.yaw = config[5];
    }

    public float[] get(){
        return new float[] {x,y,z,roll,pitch,yaw};
    }

    public Configuration getCopy(){
        return new Configuration(this);
    }
}
