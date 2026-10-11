package com.example.form;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, password, phone, emailInput;
    private String valCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameID);
        password = findViewById(R.id.passwordID);
        phone = findViewById(R.id.phoneID);
        emailInput = findViewById(R.id.emailID);
    }

    public void SubmitForm(View view){
        String userName = name.getText().toString();
        String userPhone = phone.getText().toString();

        //validate form inputs
        boolean nameCheck = true;
        for( int i = 0; i < userName.length(); i++){
            char c = userName.charAt(i);
            if(Character.isDigit(c)){
                nameCheck = false;
            }
        }

        boolean phoneCheck = true;
        for(int i = 0; i < userPhone.length(); i++){
            char c = userPhone.charAt(i);
            if(Character.isLetter(c)){
                phoneCheck = false;
            }
        }

        if(nameCheck && phoneCheck){

            String email = emailInput.getText().toString();
            vertifyEmail(new String[]{email}, "Email Validation Code");

            Intent vertifyIntent = new Intent(this, VertifyEmail.class);
            vertifyIntent.putExtra("code", valCode);
            startActivity(vertifyIntent);
            }
        else {
            Toast.makeText(this, "Invalid input, please recheck your details entered.",  Toast.LENGTH_LONG).show();
        }
    }

    public void vertifyEmail(String[] addresses, String subject) {
        //generate validation code
        valCode = String.valueOf((int) (Math.random() * 100) + 1);

        //create implicit intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //Only the email app wil handle this intent, no others.
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Your code is " + valCode);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Email app not found.", Toast.LENGTH_LONG).show();
        }
    }
}
