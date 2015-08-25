package com.abdroid.wps2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Add extends Activity {
    Button s,a;
    EditText et1;
    EditText et2;
    EditText et3;
    DatabaseHandler db;

    Double lat,lng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toast.makeText(this, "Add & View Access Points", Toast.LENGTH_SHORT).show();

        db = new DatabaseHandler(this, null, null, 1);


        s = (Button) findViewById(R.id.button2); //save
        a = (Button) findViewById(R.id.button3); //get automatic gps location
        et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        et3 = (EditText) findViewById(R.id.editText5);


        poplist();

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Inserting

                db.addap(new Apinfo(et1.getText().toString(), et2.getText().toString(), et3.getText().toString()));

                Log.d("Insert: ", "Inserting ..");
                poplist();
                et1.setText("");
                et2.setText("");
                et3.setText("");


            }

        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get lat long through gps

                LocationManager lm =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                lat=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
                lng=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
                et2.setText(lat.toString());
                et3.setText(lng.toString());

            }
        });

    }


   public void poplist()
    {

        //ListView


        DatabaseHandler d=new DatabaseHandler(this, null,null, 1);

        Cursor c1=d.getAllRows();
        startManagingCursor(c1);
        String[] fromFieldNames=new String[] {d.key_id,d.mac,d.lat,d.longt};
        int[] toView=new int[] {R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5};

        SimpleCursorAdapter s=new SimpleCursorAdapter(this,R.layout.row_layout,c1,fromFieldNames,toView);

        ListView l = (ListView) findViewById(R.id.listView2);

        l.setAdapter(s);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

}
