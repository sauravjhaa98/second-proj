package com.example.admin.navidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forgot_password extends AppCompatActivity {
    String url="http://sauravvv.com/webservice/forgot_pass.php";
    EditText e1,e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        e1=(EditText)findViewById(R.id.fgt_email);
        e2=(EditText)findViewById(R.id.fgt_pass);
        b1=(Button)findViewById(R.id.fgt_btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";


                String Email = e1.getText().toString();


                Matcher matcher= Pattern.compile(validemail).matcher(Email);


                if (matcher.matches()){


                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email-Id",Toast.LENGTH_LONG).show();
                }


                if (e2.getText().toString().equals("")){
                    e2.setError("Enter password");
                }




                Checked();
                sendData();
            }

        });
    }
    public void sendData(){

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONObject obj=new JSONObject(response);
                    String s=obj.getString("status");

                    if(s.equals("1")){
                        cln();

                        Toast.makeText(Forgot_password.this, "Data Submited..", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Forgot_password.this, "Server Error..?", Toast.LENGTH_LONG).show();
                    }




                }catch (Exception t){
                    cln();
                    Toast.makeText(Forgot_password.this, "Server Error..?"+t, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){

            protected Map<String,String> getParams(){
                Map<String,String>mp=null;

                mp=new Hashtable<String, String>();

                mp.put("email",e1.getText().toString());
                mp.put("password",e2.getText().toString());


                return  mp;
            }
        };
        RequestQueue q= Volley.newRequestQueue(Forgot_password.this);

        q.add(request);
    }

    public void cln(){
        e1.setText(null);
        e2.setText(null);


    }
    Boolean isEmpty(EditText  e1) {
        CharSequence str = e1.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void Checked(){
        if(isEmpty(e1)){
            e1.setError("Name is required");
        }
        if(isEmpty(e2)){
            e2.setError(" Password is Required");
        }




    }

    public void logout(View view) {
        new User(Forgot_password.this).removeUser();
        Intent i= new Intent(Forgot_password.this,Splash.class);
        startActivity(i);
        finish();

    }
}
