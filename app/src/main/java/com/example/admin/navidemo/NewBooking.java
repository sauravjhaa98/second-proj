package com.example.admin.navidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewBooking extends AppCompatActivity {

    String url = "http://sauravvv.com/arkle/insert.php";
    Button b1;
    EditText e1,e2,e3,e4;
   CalendarView c1;
   TextView t1;
static  String Name,Date,Time_slot,Type,MobileNo;
static String namepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);
        e1=(EditText) findViewById(R.id.input_name);
        e2=(EditText) findViewById(R.id.input_address);
        e3=(EditText) findViewById(R.id.input_slot);
        e4=(EditText) findViewById(R.id.input_type);
        c1=(CalendarView)findViewById(R.id.calender);
        t1=(TextView)findViewById(R.id.date);
        b1=(Button)findViewById(R.id.crb);

        c1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = ((i1 + 1)) + "/" + (i2) + "/" + i;
                t1.setText(date);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitappointment();
               Name=e1.getText().toString();
               Date=t1.getText().toString();
               Time_slot=e3.getText().toString();
               Type=e4.getText().toString();
               namepass=e1.getText().toString();
                Intent q=new Intent(NewBooking.this,Booking_Acknowledgement.class );
               startActivity(q);

            }
        });
    }


    public void submitappointment() {
        StringRequest st = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONObject obj=new JSONObject(response);
                    String s=obj.getString("status");

                    if(s.equals("1")){
                        cln();



                        Toast.makeText(NewBooking.this, "Data Submited..", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(NewBooking.this, "Server Error..?", Toast.LENGTH_LONG).show();
                    }




                }catch (Exception t){
                    cln();
                    Toast.makeText(NewBooking.this, "Server Error..?"+t, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NewBooking.this, "Server Error..", Toast.LENGTH_SHORT).show();


            }

        }) {

            protected Map<String, String> getParams() {

                Map<String, String> mp = null;

                mp = new HashMap<String, String>();

                mp.put("pname", e1.getText().toString());
                mp.put("date", t1.getText().toString());
                mp.put("time",e3.getText().toString());
                mp.put("appoint",e4.getText().toString());
                mp.put("mobile", e2.getText().toString());


                return mp;
            }


        };

        RequestQueue q = Volley.newRequestQueue(NewBooking.this);

        q.add(st);

    }


    public void cln() {
        e1.setText(null);
        e1.requestFocus();
      t1.setText(null);
        e2.setText(null);
       e4.setText(null);
       e3.setText(null);

   }

}


