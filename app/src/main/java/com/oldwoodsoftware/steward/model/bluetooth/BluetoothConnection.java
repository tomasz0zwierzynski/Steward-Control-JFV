package com.oldwoodsoftware.steward.model.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;

import com.oldwoodsoftware.steward.model.responsibility.listener.BluetoothDataListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothConnection {

    private final int BUFFER_SIZE = 255;

    private BluetoothSocket _socket = null;
    private BluetoothAdapter _adapter;
    private BluetoothDevice _device;

    private boolean stop_request=false;
    private Thread readThread;

    public byte[] _data;

    private Activity mainActivity;
    private BluetoothDataListener btReciever;

    private void getAdapter() {
        _adapter = BluetoothAdapter.getDefaultAdapter();
    }
    private void getDevice() {
        final List<BluetoothDevice> pairedDevices = new ArrayList<BluetoothDevice>(_adapter.getBondedDevices());
        for (BluetoothDevice device : pairedDevices) {
            if (device.getName().toUpperCase().startsWith("HC-05")){
               _device = device;
            }
        }
    }

    public BluetoothConnection(final Activity parent) throws Exception{
        mainActivity = parent;
        btReciever = (BluetoothDataListener) mainActivity;
        //We creating connection when constructing new object

        //Get the adapter
        getAdapter();
        if (_adapter == null) {
            throw new Exception("No default bluetooth adapter found!");
        }

        //Check if bluetooth is turned on
        if (_adapter.isEnabled() == false){
            throw new Exception("Turn on bluetooth!");
        }

        //Get the device from paired devices
        getDevice();
        if (_device == null){
            throw new Exception("Cannot found HC-05 device!");
        }

        //Create communication socket
        _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
         if (_socket == null){
            throw new Exception("Error while creating RfcommSocket");
         }

        //TODO: make running this whole connect thing in another thread or something it blocks UI
        //Simply connect to the device
        _socket.connect();

        if (_socket.isConnected() == false){
            throw new Exception("Error while establishing connection!");
        }

        _data = new byte[BUFFER_SIZE];

        readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < _data.length; i++){
                    _data[i] = 0;
                }

                Handler mainHandler = new Handler(Looper.getMainLooper()); //Used later

                //Why java not allow to define enum here, classes can be defined...
                int state=0; //0-data, 1-term_r 2-term_n
                int ctr=0;
                byte next;
                boolean whole_msg=false;

                while (stop_request == false)
                {
                    try
                    {
                        if(_socket.getInputStream().available() > 0){
                            //Some decoder maybe or something???
                            next = (byte)_socket.getInputStream().read();
                            switch (state){
                                case 0: //data
                                    if (next == '\r'){
                                        state = 1;
                                    }
                                    break;
                                case 1: //term_r
                                    if (next == '\n'){
                                        state = 2;
                                        whole_msg = true;
                                    }
                                    break;
                                case 2: //term_n
                                    state = 0;
                                    ctr=0;
                                    break;
                                default:
                                    break;
                            }
                            _data[ctr] = next;
                            ctr++;
                            //handle data and post it to main
                            if (whole_msg){
                                Runnable myRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            btReciever.onBluetoothData(_data);
                                        }catch (Exception ex){
                                        }
                                    }
                                };
                                mainHandler.post(myRunnable);
                                whole_msg = false;
                            }

                        }else{
                            this.wait(100);
                        }
                    }
                    catch (Exception ex)
                    { }
                }
            }
        });
        readThread.start();

    }

    public boolean isConnected(){
        if (_socket != null){
            return _socket.isConnected();
        }else {
            return false;
        }
    }

    public void disconnect() throws Exception{
        stop_request = true;
        readThread.join();
        _socket.close();

    }

    public void sendMessage(byte[] buffer) throws Exception{
        for (int i = 0; i < buffer.length; i++){
            _socket.getOutputStream().write(buffer[i]);

        }
        _socket.getOutputStream().write('\r');
        _socket.getOutputStream().write('\n');
    }

}
