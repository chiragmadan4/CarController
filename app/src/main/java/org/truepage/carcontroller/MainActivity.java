package org.truepage.carcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    String address = null;

    public void send(View view) {
//        new ConnectBT().execute();
//        if(btSocket!=null){
//            try{
//                btSocket.getOutputStream().write("f".getBytes());
//                Log.i("msg",address);
//            }
//            catch (Exception e){
//                Log.e("msg","exception in send method",e);
//            }
//        }else{
//            Log.i("msg","btsocket null");
//        }
        Intent i = new Intent(MainActivity.this, ControllerActivity.class);
        i.putExtra("address", address);
        startActivity(i);

    }

    public void disable(View view) {
        BA.disable();
        if (!BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Could not turn off bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }

    public void showPaired(View view) {
        Set<BluetoothDevice> set = BA.getBondedDevices();
        ListView listView = findViewById(R.id.listView);
        ArrayList arr = new ArrayList();
        for (BluetoothDevice bd : set) {
            arr.add(bd.getName() + "\n" + bd.getAddress());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = ((TextView) view).getText().toString();
                address = info.substring(info.length() - 17);
            }
        });
    }

    public void find(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BA = BluetoothAdapter.getDefaultAdapter();
    }

}