package com.abdroid.wps2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Handler;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    ListView lv;
    WifiManager wifi;
    String wifis[];
    WifiScanReceiver wifiReciever;

    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        wifiReciever = new WifiScanReceiver();
        wifi.startScan();

        b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Add.class);
                i.putExtra("mac","");
                startActivity(i);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(MainActivity.this, Add.class);
                i.putExtra("mac",wifiReciever.getSsid(position));
                startActivity(i);
                return false;
            }
        });

    }

    protected void onPause() {
        unregisterReceiver(wifiReciever);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class WifiScanReceiver extends BroadcastReceiver {

        List<ScanResult> wifiScanList = wifi.getScanResults();
        public String getSsid(int pos) {

            return ssid[pos];
        }

        public String ssid[]=new String[wifiScanList.size()];



        public void onReceive(Context c, Intent intent) {



            wifis = new String[wifiScanList.size()];

            //int j = 0;


            // int count = 1; String etWifiList = "";


            for (int i = 0; i < wifiScanList.size(); i++) {
                wifis[i] = (wifiScanList.get(i)).toString();
                wifis[i] += "DISTANCE : ";
                wifis[i] += calculateDistance(wifiScanList.get(i).level,wifiScanList.get(i).frequency);
                ssid[i]=wifiScanList.get(i).BSSID;

            }


          /*  for (ScanResult result : wifiScanList) {
                // etWifiList = count++ + ". " + result.SSID;


                wifis[j] += calculateDistance(result.level, result.frequency);
                j++;



            }  */
            lv.setAdapter(new ArrayAdapter<String>(

                    getApplicationContext(), android

                    .R.layout.simple_list_item_1, wifis));
            wifi.startScan();


        }


        public double calculateDistance(double signalLevelInDb, double freqInMHz) {
            double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
            return Math.pow(10.0, exp);
        }

    }

}
