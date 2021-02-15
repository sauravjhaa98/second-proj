package com.example.admin.navidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Appointment_Details extends AppCompatActivity {
    EditText PhoneNumber;
    Button getDetails;
    String getPhoneNumber;
    String MobilePattern = "[0-9]{10}";
    Matcher m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment__details);
        PhoneNumber=(EditText)findViewById(R.id.mobileNo);
        getDetails=(Button)findViewById(R.id.getdetails);
        getSupportActionBar().setTitle("Your Booking Details ");
        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(Appointment_Details.this, "Please Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                } else {
                    if (PhoneNumber.getText().toString().matches(MobilePattern)) {
                        getPhoneNumber = PhoneNumber.getText().toString().trim();
                        Intent i = new Intent(Appointment_Details.this, Appointment_page.class);
                        i.putExtra("phone", getPhoneNumber);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Appointment_Details.this, "Enter Correct Mobile Number", Toast.LENGTH_LONG).show();
                    }
                }
            }



        });

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

}
