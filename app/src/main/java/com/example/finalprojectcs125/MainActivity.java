package com.example.finalprojectcs125;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFlightActivity();
            }
        });

        button1 = (Button)findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVirusActivity();     }
        });
    }



    public void openFlightActivity() {
        Intent intent = new Intent(this, flight.class);
        startActivity(intent);

    }

    public void openVirusActivity() {
        Intent intent = new Intent(this, virus.class);
        startActivity(intent);
        queue.cancelAll(TAG_SEARCH_NAME);

        // first StringRequest: getting items searched
        StringRequest stringRequest = searchNameStringRequest(txtSearch.getText().toString());
        stringRequest.setTag(TAG_SEARCH_NAME);

        // executing the request (adding to queue)
        queue.add(stringRequest);
    }



}
