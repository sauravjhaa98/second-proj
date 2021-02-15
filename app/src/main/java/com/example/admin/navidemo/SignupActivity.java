package com.example.admin.navidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    String url="http://imsauravvv.xyz/webservices/signup.php";;

    EditText e1,e2,e3,e4,e5;
    TextView tx1;
    Button btn;
    boolean isNameValid,isAddressValid,isEmailValid, isMobileValid,isPasswordValid;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        e1=(EditText)findViewById(R.id.input_name);
        e2=(EditText)findViewById(R.id.input_address);
        e3=(EditText)findViewById(R.id.input_email);
        e4=(EditText)findViewById(R.id.input_mobile);
        e5=(EditText)findViewById(R.id.input_password);
        btn=(Button)findViewById(R.id.crb);
        tx1=(TextView)findViewById(R.id.link_login);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p= new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(p);
                finish();


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();

                if(e1.getText().toString().trim().isEmpty() && e2.getText().toString().trim().isEmpty() && e3.getText().toString().trim().isEmpty() && e4.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Please Enter values  ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(e3.getText().toString().trim().matches(emailPattern))
                    {
                        sendData();
                    }
                    else
                    {

                }}


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
                        Toast.makeText(SignupActivity.this, "Data Submited..", Toast.LENGTH_LONG).show();
                        Intent u= new Intent(SignupActivity.this,LoginActivity.class);
                        startActivity(u);
                        finish();

                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Server Error..?", Toast.LENGTH_LONG).show();
                    }




                }catch (Exception t){
                    cln();
                    Toast.makeText(SignupActivity.this, "Server Error..?"+t, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {

            protected Map<String,String> getParams(){
                Map<String,String>mp=null;

                mp=new Hashtable<String, String>();
                mp.put("name",e1.getText().toString());
                mp.put("address",e2.getText().toString());
                mp.put("email",e3.getText().toString());
                mp.put("contact",e4.getText().toString());
                mp.put("password",e5.getText().toString());


                return  mp;
            }
        };
        RequestQueue q= Volley.newRequestQueue(SignupActivity.this);

        q.add(request);
    }

    public void cln(){
        e1.setText(null);
        e1.requestFocus();
        e2.setText(null);
        e3.setText(null);
        e4.setText(null);
        e5.setText(null);

    }
    public void Validation() {

        if (e1.getText().toString().isEmpty()) {
            e1.setError("please Enter Your Name");
            isNameValid = false;
        } else if (e1.getText().length() < 6) {
            e1.setError("Name should be at least more than 6 character ");
            isNameValid = false;
        } else  {
            isNameValid = true;
        }

        if (e2.getText().toString().isEmpty()) {
            e2.setError("Please Enter your address ");
            isAddressValid = false;
        } else if (e2.getText().length() < 6) {
            e2.setError("Address should be at least 8 char");
            isAddressValid = false;
        } else  {
            isAddressValid = true;

        }
        if (e3.getText().toString().isEmpty()) {
            e3.setError("Please Enter your email ");
            isEmailValid = false;
        } else if (e3.getText().length() < 6) {
            e3.setError("Email should be 8 char");
            isEmailValid = false;
        } else  {
            isEmailValid = true;

        }
        if (e4.getText().toString().isEmpty()) {
            e4.setError("Enetr Mobile number ");
            isMobileValid = false;
        } else if (e4.getText().length() < 6) {
            e4.setError("Mob Number should be 8 char");
            isMobileValid = false;
        } else  {
            isMobileValid = true;

        }
        if (e5.getText().toString().isEmpty()) {
            e5.setError("Enetr password ");
            isPasswordValid = false;
        } else if (e5.getText().length() < 6) {
            e5.setError("Password should be 8 char");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;

        }

        if (isEmailValid && isPasswordValid && isMobileValid && isAddressValid && isNameValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    }

