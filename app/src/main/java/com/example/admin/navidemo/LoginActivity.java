package com.example.admin.navidemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class LoginActivity extends AppCompatActivity {
    String url="http://imsauravvv.xyz/webservices/login.php";
    Button b1,b2;
    EditText e1,e2;
    TextView t1;
    ProgressDialog pr;
    static String email;
    SharedPreferences sharedPreferences;
    String val, val2;
     boolean isEmailValid,isPasswordValid;
     public  String stm;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RadioGroup gb;
    RadioButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.password);
        t1 = (TextView)findViewById(R.id.link_signup);
        b1 = (Button)findViewById(R.id.btn_login);
        b2 = (Button)findViewById(R.id.btn_reset_password);
        gb = (RadioGroup) findViewById(R.id.radio);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  getSupportActionBar().setTitle("Login Activity ");
        getSupportActionBar().hide();
        stm=e1.getText().toString();


         sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        if(sharedPreferences.contains("username") && sharedPreferences.contains("password")){
             startActivity(new Intent(LoginActivity.this,Bookappointment.class));
              finish();   //finish current activity
              }

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        gb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int myid = gb.getCheckedRadioButtonId();
                bt = (RadioButton) findViewById(myid);
                if (bt.getText().toString().equals("Admin ")) {


                } else {

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                User user= new User(LoginActivity.this);
                user.setName(stm);
                Validation();
                int myid = gb.getCheckedRadioButtonId();
                bt = (RadioButton) findViewById(myid);
                if (bt.getText().toString().equals("Admin")) {

                    String email = e1.getText().toString();
                    String password = e2.getText().toString();
                    if (email.equals("admin21@gmail.com") && password.equals("admin21@12345")) {
                        Intent i = new Intent(LoginActivity.this, BookedData.class);
                        startActivity(i);
                            cln();
                            finish();
                    }


                }
                val=e1.getText().toString();
                val2=e2.getText().toString();
                if (val.trim().isEmpty() && val2.trim().isEmpty()  )
                {
                    Toast.makeText(LoginActivity.this, "Please Enter  Email and Password ", Toast.LENGTH_LONG).show();
                }
                else {
                    if ( val.trim().matches(emailPattern) && bt.getText().toString().equals("Patitent")) {

                        loginwithVolley();

                    }


                    else {
                        Toast.makeText(LoginActivity.this, "here ", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent t=new Intent(LoginActivity.this,Forgot_password.class);
               // startActivity(t);
            }
        });

    }



    public void loginwithVolley(){

        pr=new ProgressDialog(LoginActivity.this);
        pr.setMessage("Please Wait...");
        pr.show();
        final StringRequest res=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                takeResponse(response);
               // Toast.makeText(LoginActivity.this, "Response="+ response , Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            protected Map<String,String> getParams(){

                Map<String,String>mp=null;

                mp=new Hashtable<String, String>();

                mp.put("email",e1.getText().toString());
                mp.put("password",e2.getText().toString());

                return  mp;
            }


        };

        RequestQueue q= Volley.newRequestQueue(LoginActivity.this);

        q.add(res);

    }

    public void takeResponse(String rs){

        try{

            JSONObject obj=new JSONObject(rs);

            String response=obj.getString("status");

            if(response.equals("1")){

                pr.dismiss();
                SharedPreferences.Editor sp = sharedPreferences.edit();
                sp.putString("username", val);
                sp.putString("password", val2);
                sp.commit();


                Intent i=new Intent(LoginActivity.this,Bookappointment.class);

                startActivity(i);
                finish();
                cln();
            }
            else{
                pr.dismiss();
                cln();
                Toast.makeText(this, "Invalid User and Password...?", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception t)
        {

        }

    }

    public void cln(){
        e1.setText(null);
        e2.setText(null);
        e2.requestFocus();

    }
    public void Validation() {

        if (e1.getText().toString().isEmpty()) {
            e1.setError("enter you email");
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(e1.getText().toString()).matches()) {
            e1.setError("enter your correct email");
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        if (e2.getText().toString().isEmpty()) {
            e2.setError("Enetr password ");
            isPasswordValid = false;
        } else if (e2.getText().length() < 6) {
            e2.setError("Password should be 8 char");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;

        }

        if (isEmailValid && isPasswordValid) {

        }

    }

}
