package com.example.code_challenge1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextEmail = findViewById(R.id.edit_text_email);
        EditText editTextPhone = findViewById(R.id.edit_text_phone);

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(editTextEmail.getText());
        if (!matcher.matches())
        {
            editTextEmail.setError("Invalid email address.");
        }

        regex = "^/d(?:-/d{3}){3}/d$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(editTextPhone.getText());
        if (!matcher.matches())
        {
            editTextPhone.setError("Invalid phone number.");
        }







    }
}
