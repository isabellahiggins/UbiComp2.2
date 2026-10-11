package com.example.form;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VertifyEmail extends AppCompatActivity {

    EditText codeInput;
    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vertifyemail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codeInput = findViewById(R.id.codeInput);
        codeSent = getIntent().getExtras().getString("code");

    }

    public void vertifyCode(View view){
        String  inputtedCode = codeInput.getText().toString();

        if(inputtedCode.equals(codeSent)){
            Toast.makeText(this, "Thank you for validating your email", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Validation code entered is incorrect. Please check your email.", Toast.LENGTH_LONG).show();
        }


    }
}


