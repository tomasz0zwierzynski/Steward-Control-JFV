package com.oldwoodsoftware.steward.core.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.oldwoodsoftware.steward.platform.event.BluetoothEventListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BluetoothConnection {

    private final int BUFFER_SIZE = 16;

    private BluetoothSocket _socket = null;
    private BluetoothAdapter _adapter;
    private BluetoothDevice _device;

    private boolean isReadThreadStopRequest =false;
    private Thread readThread;
    private Handler mainHandler;

    public byte[] _data = new byte[BUFFER_SIZE];

    private List<BluetoothEventListener> btReceivers = new ArrayList<>();

    public void addBluetoothListener(BluetoothEventListener bel){
        btReceivers.add(bel);
    }

    public void connect() throws Exception{
        Log.i("BluetoothConnection","connect() function called");
        if (_socket == null){
            Log.e("BluetoothConnection","_socket is null");
            throw new Exception("Socket not created properly...");
        }

        emitConnectionStateChanged(BluetoothState.connecting);

        Thread connectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("BluetoothConnection","connectThread.run() called");
                boolean success = true;
                try {
                    _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
                    _socket.connect();
                } catch (IOException e) {
                    success = false;
                    e.printStackTrace();
                } finally {
                    final boolean fSuccess;
                    if (success){
                        fSuccess = true;
                    }else{
                        fSuccess = false;
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            connectCallback(fSuccess);
                        }
                    });
                }
            }
        });
        connectThread.start();
        emitMessage("Connect thread started.");

        isReadThreadStopRequest = false;
    }

    private void connectCallback(boolean isConnected){
        Log.i("BluetoothConnection","connectCallback( " + String.valueOf(isConnected) + ") called");
        if (isConnected){
            emitConnectionStateChanged(BluetoothState.connected);
            createReadThread();
        }else{
            emitConnectionStateChanged(BluetoothState.error);
            emitMessage("Could not connect to device. ");
        }
    }

    public boolean isInitialized(){
        return ( (_adapter != null) && (_adapter.isEnabled()) && (_device != null) && (_socket != null));
    }

    public void init() throws Exception{
        Log.i("BluetoothConnection","init() called");

        getAdapter();
        if (_adapter == null) {
            emitMessage("No default bluetooth adapter found!");
            throw new Exception("No default bluetooth adapter found!");
        }

        if (_adapter.isEnabled() == false){
            emitMessage("Bluetooth is turned off!");
            throw new Exception("Turn on bluetooth!");
        }

        getDevice();
        if (_device == null){
            emitMessage("Cannot find HC-05 device!");
            throw new Exception("Cannot found HC-05 device!");
        }

        _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
        if (_socket == null){
            emitMessage("Could not create Rfcomm socket!");
            throw new Exception("Error while creating RfcommSocket");
        }

        //Get Handler to deal with threads
        mainHandler = new Handler(Looper.getMainLooper());
    }

    private void createReadThread(){
        Log.i("BluetoothConnection","createReadThread() called");
        emitMessage("Creating reading thread.");
        readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < _data.length; i++){
                    _data[i] = 0;
                }

                int ctr=0;
                byte next;

                while (isReadThreadStopRequest == false)
                {
                    try
                    {
                        if(_socket.getInputStream().available() > 0){
                            //Some decoder maybe or something???
                            next = (byte)_socket.getInputStream().read();
                            if(next == '\n'){
                                _data[ctr] = 0;
                                ctr=0;
                                final byte[] pack = Arrays.copyOf(_data,BUFFER_SIZE);
                                Runnable myRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            emitDataReceived(pack);
                                        } catch (Exception ex) {
                                        }
                                    }
                                };
                                mainHandler.post(myRunnable);
                                for (int i = 0; i < _data.length; i++) {
                                    _data[i] = 0;
                                }
                            }else if (next != '\r'){
                                _data[ctr++] = next;
                            }
                        }else{
                            this.wait(100);
                        }
                    }
                    catch (Exception ex){                    }
                }
                Log.i("BluetoothConnection","readThread is about to finish");
            }
        });
        readThread.start();
    }

    public BluetoothConnection(){

    }

    public boolean isConnected(){
        if (_socket != null){
            return _socket.isConnected();
        }else {
            return false;
        }
    }

    public void disconnect() throws Exception{
        emitConnectionStateChanged(BluetoothState.disconnecting);

        isReadThreadStopRequest = true;
        readThread.join();
        _socket.close();

        emitMessage("Disconnected from device.");
        emitConnectionStateChanged(BluetoothState.disconnected);
    }

    public void sendMessage(byte[] buffer) throws Exception{
        //If isConnected==false then don't send anything!!!
        for (int i = 0; i < buffer.length; i++){
            _socket.getOutputStream().write(buffer[i]);

        }
        _socket.getOutputStream().write('\r');
        _socket.getOutputStream().write('\n');

        emitMessage("Buffer send: " + new String(buffer, StandardCharsets.UTF_8));
                    System.out.println("Send: " + new String(buffer, StandardCharsets.UTF_8));
    }

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

    private void emitDataReceived(byte[] data){
        Log.i("BluetoothConnection/in","Data received: " + new String(data, StandardCharsets.UTF_8));
        for (BluetoothEventListener bdl : btReceivers){
            bdl.onBluetoothDataReceived(data);
        }
    }

    private void emitConnectionStateChanged(BluetoothState btStatus){
        for (BluetoothEventListener bdl : btReceivers){
            bdl.onBluetoothConnectionStateChanged(btStatus);
        }
    }

    private void emitMessage(String msg){
        for (BluetoothEventListener bdl : btReceivers){
            bdl.onBluetoothMessage(msg);
        }
    }

    @Override
    public String toString(){
        return "core.bluetooth." + this.getClass().getSimpleName() + ", registered listeners " + btReceivers.toString() + "/n Connected: " + String.valueOf(isConnected());
    }

}
