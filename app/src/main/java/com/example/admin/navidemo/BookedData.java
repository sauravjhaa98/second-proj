package com.example.admin.navidemo;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookedData extends AppCompatActivity {
     List <Usser> list_data;
     RecyclerView rv;
     UserAdapter adapter;
    String URL_Data="http://imsauravvv.xyz/webservices/getdataa.php";
    RequestQueue reqQue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_data);
        rv=(RecyclerView)findViewById(R.id.recyleview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},1234);
        list_data=new ArrayList<>();
        adapter=new UserAdapter(list_data);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //  getSupportActionBar().setTitle("Login Activity ");
        getSupportActionBar().hide();

        getMovieData();

    }

    private void getMovieData() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_Data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        Usser listData=new Usser(ob.getString("pname")
                                            ,ob.getString("mobile")
                                            , ob.getString("time")
                                            ,ob.getString("date")
                                            ,ob.getString("appoint"));
                        list_data.add(listData);
                    }
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}