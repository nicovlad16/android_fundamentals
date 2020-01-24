package com.example.curs2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);

        final ImageView image = findViewById(R.id.image);

        final EditText edit = findViewById(R.id.edit);

        final CheckBox checkBox = findViewById(R.id.checkbox);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "button pressed", Toast.LENGTH_LONG).show();

//                image.setImageResource(R.drawable.icon);

//                Toast.makeText(MainActivity.this, edit.getText().toString(), Toast.LENGTH_LONG).show();

                boolean isChecked = checkBox.isChecked();
                Toast.makeText(MainActivity.this, isChecked ? "is checked" : "is not checked", Toast.LENGTH_LONG).show();


            }
        });
    }
}
