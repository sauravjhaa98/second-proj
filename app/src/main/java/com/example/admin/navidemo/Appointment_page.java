package com.example.admin.navidemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class Appointment_page extends AppCompatActivity {
    TextView Tt1,tt2,tt3,tt4,tt5;
    String PhoneNumber;
     String   URL_Data="http://imsauravvv.xyz/webservices/getData_with_idd.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_page);
        Tt1=(TextView)findViewById(R.id.first);
        tt2=(TextView)findViewById(R.id.second);
        tt3=(TextView)findViewById(R.id.third);
        tt4=(TextView)findViewById(R.id.fourth);
        tt5=(TextView)findViewById(R.id.fifth);
        Intent intent= getIntent();
        PhoneNumber=intent.getStringExtra("phone");
        getSupportActionBar().setTitle("Appointment Details ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       //Toast.makeText(this, "Value"+PhoneNumber, Toast.LENGTH_SHORT).show();
        SgetData();
    }
    private void SgetData() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_Data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  //Toast.makeText(Appointment_page.this, "Resonpse::"+response, Toast.LENGTH_LONG).show();
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
                        Tt1.setText(Sname);
                        tt2.setText(Saddress);
                        tt3.setText(Semail);
                        tt4.setText(Scontact);
                        tt5.setText(Spassword);
                       // Toast.makeText(Appointment_page.this, "AV"+Sname+""+Semail+""+Saddress+Scontact+Spassword, Toast.LENGTH_SHORT).show();


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
                params.put("mobile",PhoneNumber);
                return params;
            }

        } ;

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
                                startActivity(new Intent(Appointment_page.this,LoginActivity.class));
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
    //

}
