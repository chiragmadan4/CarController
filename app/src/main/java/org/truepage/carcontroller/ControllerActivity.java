package org.truepage.carcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.UUID;

public class ControllerActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    String address;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        address = getIntent().getExtras().getString("address");
        new ConnectBT().execute();
    }
    public void forward(View view){

        if(btSocket!=null){
            try{
                btSocket.getOutputStream().write("f".getBytes());
                Log.i("msg",address);
            }
            catch (Exception e){
                Log.e("msg","exception in send method",e);
            }
        }else{
            Log.i("msg","btsocket null");
        }
    }
    public void backward(View view){

        if(btSocket!=null){
            try{
            btSocket.getOutputStream().write("b".getBytes());
            Log.i("msg",address);
            }
            catch (Exception e){
            Log.e("msg","exception in send method",e);
            }
        }else{
        Log.i("msg","btsocket null");
        }
    }
    public void right(View view){

        if(btSocket!=null){
            try{
                btSocket.getOutputStream().write("r".getBytes());
                Log.i("msg",address);
            }
            catch (Exception e){
                Log.e("msg","exception in send method",e);
            }
        }else{
            Log.i("msg","btsocket null");
        }
    }
    public void left(View view){

        if(btSocket!=null){
            try{
                btSocket.getOutputStream().write("l".getBytes());
                Log.i("msg",address);
            }
            catch (Exception e){
                Log.e("msg","exception in send method",e);
            }
        }else{
            Log.i("msg","btsocket null");
        }
    }
    public void stop(View view){

        if(btSocket!=null){
            try{
                btSocket.getOutputStream().write("s".getBytes());
                Log.i("msg",address);
            }
            catch (Exception e){
                Log.e("msg","exception in send method",e);
            }
        }else{
            Log.i("msg","btsocket null");
        }
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    BA = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = BA.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                Log.i("msg","Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                Log.i("msg","Connected.");
                isBtConnected = true;
            }
        }
    }
}




//
//
//new ConnectBT().execute();
//        if(btSocket!=null){
//        try{
//        btSocket.getOutputStream().write("f".getBytes());
//        Log.i("msg",address);
//        }
//        catch (Exception e){
//        Log.e("msg","exception in send method",e);
//        }
//        }else{
//        Log.i("msg","btsocket null");
//        }