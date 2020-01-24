package com.example.curs5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // activity for result
//        if (getIntent().hasExtra("key")) {
//            String newLabel = getIntent().getStringExtra("key");
//            TextView textView = findViewById(R.id.label);
//            textView.setText(newLabel);
//        }


        if (savedInstanceState != null) {
            String text = savedInstanceState.getString("savedString");
            EditText editText = findViewById(R.id.editText);
            editText.setText(text);
        }

        findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextEntry = ((EditText) findViewById(R.id.editText)).getText().toString();
                Intent intent = new Intent();
                intent.putExtra("result", editTextEntry);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String editTextEntry = ((EditText) findViewById(R.id.editText)).getText().toString();
        outState.putString("savedString", editTextEntry);
    }
}
