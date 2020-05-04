package com.example.finalprojectcs125;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class virus extends AppCompatActivity {

    Button button2;
    TextView virus_data;
    RequestQueue requestQueue;
    String url = "https://api.covid19api.com/live/country/united-states";

    private void setText(String province, int conf, int deaths, int active) {
        this.virus_data.setText(province);
        this.virus_data.setText(conf);
        this.virus_data.setText(deaths);
        this.virus_data.setText(active);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virus);
        this.button2 = (Button) findViewById(R.id.button2);  // Link to button.
        this.virus_data = (TextView) findViewById(R.id.virus_data);  // Link to text output box.
        this.virus_data.setMovementMethod(new ScrollingMovementMethod());

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            //looping through the data
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // Getting the data
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String state = jsonObj.get("Province").toString();
                                    int confirmed = jsonObj.getInt("Confirmed");
                                    int deaths = jsonObj.getInt("Deaths");
                                    int active = jsonObj.getInt("Active");
                                    virus_data.append(state  + " " + "\n" + "Confirmed Cases: " + String.valueOf(confirmed) + "\n" + "Deaths: " + String.valueOf(deaths) + "\n" + "Active Cases: " + String.valueOf(active) + "\n\n");
                                } catch (JSONException e) {
                                    // What to do if you get an Error in Json Object
                                    Log.e("Volley", "Invalid JSON Object.");
                                }


                            }

                        }

                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        setText("none", 0,0,0);
                        Log.e("Volley", error.toString());
                    }
                }
        );


        //Handling the request
        requestQueue.add(arrReq);

    }
}