package com.example.admin.navidemo;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bookappointment extends AppCompatActivity {

    String url = "http://imsauravvv.xyz/webservices/appointment.php";
    EditText t1, t2, t3, t4, t5;
    TextView txt;
    TextView tt1, tt2;
    Button submit, cancel;
    CalendarView cv;
    String s1, s2, s3, s4, s5;
    String selecteditem1, selecteditem2;
    String time, appoint;
    static String Date;
     static  String Slot_time;
     static  String Appointment_type;
     static  String Mobile_no;
    String MobilePattern = "[0-9]{10}";
     Matcher m;
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";




    Spinner spin1, spin2;

    String str[] = {"Select Time Slot", "10:00 am-12:00 pm", "12:00 pm-2:00 pm", "2:00 pm-4:00 pm", "4:00 pm-6:00 pm", "6:00 pm-8:00 pm"};

    String str1[] = {"Appointment Type", "Skin Care", "Hair Care", "Body Slimming", "Laser Services", "Skin Diseases", "Weight Loss", "Diet", "Meditation", "Yoga"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);

        t1 = (EditText) findViewById(R.id.editText);
        tt1 = (TextView) findViewById(R.id.date);
        //t3 = (EditText) findViewById(R.id.editText3);
        //t4 = (EditText) findViewById(R.id.editText4);
        t5 = (EditText) findViewById(R.id.editText5);

        cv = (CalendarView) findViewById(R.id.calender);
        txt = (TextView) findViewById(R.id.date);
        TextView txtlogout=(TextView)findViewById(R.id.Logout);

        spin1 = (Spinner) findViewById(R.id.spin1);
        spin2 = (Spinner) findViewById(R.id.spin2);
        Calendar calendar= Calendar.getInstance();
       // int date=calendar.getActualMinimum(Calendar.DATE);
       // Toast.makeText(this, "Current date is --"+date , Toast.LENGTH_LONG).show();
        cv.setMinDate(calendar.getTimeInMillis());
        getSupportActionBar().setTitle("BOOK APPOINTMENT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<String> arr1 = new ArrayAdapter<String>(Bookappointment.this, R.layout.support_simple_spinner_dropdown_item, str);
        spin1.setAdapter(arr1);




        ArrayAdapter<String> arr2 = new ArrayAdapter<String>(Bookappointment.this, R.layout.support_simple_spinner_dropdown_item, str1);
        spin2.setAdapter(arr2);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = ((i1 + 1)) + "/" + (i2) + "/" + i;
                txt.setText(date);
            }
        });
        submit=(Button)findViewById(R.id.button1);



        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecteditem1=(String)adapterView.getItemAtPosition(i);
                // Toast.makeText(HomeActivity.this, "You Have Select Station : "+s1, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecteditem2=(String)adapterView.getItemAtPosition(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


            txtlogout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.clear();
        e.commit();
        lgt();


    }
});





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               s1 = t1.getText().toString().trim();
                s2 = tt1.getText().toString().trim();
                s3 = t5.getText().toString().trim();

                if (s1.equals("")) {
                    t1.setError("please fill entry");
                }
                if (s2.equals("")) {
                    tt1.setError("please fill entry");
                }
                if (s3.equals("")) {
                    t5.setError("please fill entry");
                }

                Date=tt1.getText().toString().trim();
                Slot_time=spin1.getSelectedItem().toString().trim();
                Appointment_type=spin2.getSelectedItem().toString().trim();
                Mobile_no=t5.getText().toString().trim();
               // Toast.makeText(Bookappointment.this, " Values"+s1+" "+Date+" "+Slot_time+" "+Appointment_type+" "+Mobile_no, Toast.LENGTH_SHORT).show();

                if (s1.trim().isEmpty() || Date.trim().isEmpty() || Mobile_no.trim().isEmpty() || Date.trim().isEmpty()   )
                {
                    Toast.makeText(Bookappointment.this, "Please fill blank entery ", Toast.LENGTH_LONG).show();
                }
                else {
                    if ( s1.trim().length()>7 && Mobile_no.length()==10 && Mobile_no.matches(MobilePattern)  ) {

                      //  validate();
                        sendappointment();
                        Intent i = new Intent(Bookappointment.this,Booking_Acknowledgement.class);
                        i.putExtra("name",s1);
                        i.putExtra("date",Date);
                        i.putExtra("time",Slot_time);
                        i.putExtra("appointment",Appointment_type);
                        i.putExtra("mobile",Mobile_no);
                        startActivity(i);
                        finish();


                    } else {
                        Toast.makeText(Bookappointment.this, "Please fil Details properly ", Toast.LENGTH_LONG).show();

                    }

                }

            }



        });




    }


    public void sendappointment() {
        StringRequest st = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Bookappointment.this, ""+response, Toast.LENGTH_SHORT).show();

                try{

                    JSONObject obj=new JSONObject(response);
                    String s=obj.getString("status");

                    if(s.equals("1")){
                       // cln();

                      //  Toast.makeText(Bookappointment.this, "Data Submited..", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Bookappointment.this, "Server Error..2?", Toast.LENGTH_LONG).show();
                    }




                }catch (Exception t){
                  // cln();
                    Toast.makeText(Bookappointment.this, "Server Error..?"+t, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Bookappointment.this, "Server Error..1", Toast.LENGTH_SHORT).show();


            }

        }) {

            protected Map<String, String> getParams() {

                Map<String, String> mp = null;

                mp = new HashMap<String, String>();

                mp.put("pname", s1);
                mp.put("date", Date);
                mp.put("time",Slot_time);
                mp.put("appoint",Appointment_type);
                mp.put("mobile",Mobile_no);


                return mp;
            }


        };

        RequestQueue q = Volley.newRequestQueue(Bookappointment.this);

        q.add(st);

    }


    public void cln() {
        t1.setText(null);
        t1.requestFocus();
        t2.setText(null);
        t3.setText(null);
        t4.setText(null);
        t5.setText(null);

    }
    private boolean validate() {

        String MobilePattern = "[0-9]{10}";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (s1.length() > 23) {

            Toast.makeText(getApplicationContext(), "pls enter less the 25 character in user name", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if(!Mobile_no.matches(MobilePattern)) {

            Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return false;
    }

    public void lgt() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you want to exit?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                startActivity(new Intent(Bookappointment.this,LoginActivity.class));
                                finish();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

