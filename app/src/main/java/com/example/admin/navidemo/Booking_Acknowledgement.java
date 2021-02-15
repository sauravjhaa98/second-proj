package com.example.admin.navidemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Booking_Acknowledgement extends AppCompatActivity
{
TextView txt1,txt2,txt3,txt4,txt5;
Button bttn;
String name;
String date;
String timelot;
String appointmen_type;
String mobile_no;
String URL_Data="http://imsauravvv.xyz/webservices/getData_with_id.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__acknowledgement);
        txt1=(TextView)findViewById(R.id.text1);
        txt2=(TextView)findViewById(R.id.text2);
        txt3=(TextView)findViewById(R.id.text3);
        txt4=(TextView)findViewById(R.id.text4);
        txt5=(TextView)findViewById(R.id.text5);
        bttn=(Button)findViewById(R.id.acklogout);
        getSupportActionBar().setTitle("BOOKING DETAILS");

        Intent i=getIntent();
        name=i.getStringExtra("name");
        date=i.getStringExtra("date");
        timelot=i.getStringExtra("time");
        appointmen_type=i.getStringExtra("appointment");
        mobile_no=i.getStringExtra("mobile");
       // Toast.makeText(this, " Vlues"+ name+""+date+""+timelot+""+appointmen_type+" "+mobile_no, Toast.LENGTH_SHORT).show();

      // txt1.setText(name);
        //txt2.setText(date);
        //txt3.setText(timelot);
        //txt4.setText(appointmen_type);
        //txt5.setText(mobile_no);
        getMovieData();
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.clear();
                e.commit();
                lgt();

               
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                //logout codet
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you want to exit?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                startActivity(new Intent(Booking_Acknowledgement.this,LoginActivity.class));
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //rest of app




        private void getMovieData() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_Data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Booking_Acknowledgement.this, "Resonpse::"+response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        String Sname=ob.getString("pname").trim();
                        String Saddress=ob.getString("date").trim();
                        String Semail=ob.getString("time").trim();
                        String Scontact=ob.getString("appoint").trim();
                        String Spassword=ob.getString("mobile").trim();
                        txt1.setText(Sname);
                        txt2.setText(Saddress);
                        txt3.setText(Semail);
                        txt4.setText(Scontact);
                        txt5.setText(Spassword);
                       // Toast.makeText(Booking_Acknowledgement.this, "AV"+Sname+""+Semail+""+Saddress+Scontact+Spassword, Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }



        ) {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap <>();
                params.put("mobile",mobile_no);
                return params;
            }

        } ;

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void lgt() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you want to exit?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(Booking_Acknowledgement.this,LoginActivity.class));
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

