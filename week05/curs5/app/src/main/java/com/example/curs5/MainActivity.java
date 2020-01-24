package com.example.curs5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // explicit intent
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
////                finish();

                // implicit intent
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:1234567"));
//                startActivity(intent);

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.google.com"));
//                startActivity(intent);


                // activity for result
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", "new label"); // intent has a bundle

//                startActivity(intent);
                startActivityForResult(intent, 12345);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12345 && resultCode == Activity.RESULT_OK) {
            assert data != null;
            String result = data.getStringExtra("result");
            Button button = findViewById(R.id.button);
            button.setText(result);
        }
    }

}
