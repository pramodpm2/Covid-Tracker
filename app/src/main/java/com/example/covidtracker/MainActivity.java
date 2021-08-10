package com.example.covidtracker;//package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Create the object of TextView
    TextView tvCases, tvRecovered,
            tvCritical, tvActive,
            tvTodayCases, tvTotalDeaths,
            tvTodayDeaths,
            tvAffectedCountries;
    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link those objects with their respective id's
        // that we have given in .XML file
        tvCases
                = findViewById(R.id.tvCases);
        tvRecovered
                = findViewById(R.id.tvRecovered);
        tvCritical
                = findViewById(R.id.tvCritical);
        tvActive
                = findViewById(R.id.tvActive);
        tvTodayCases
                = findViewById(R.id.tvTodayCases);
        tvTotalDeaths
                = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths
                = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries
                = findViewById(R.id.tvAffectedCountries);

        nextPage=findViewById(R.id.next);

        fetchdata();
    }

    private void fetchdata()
    {

        // Create a String request
        // using Volley Library
        String url = "https://corona.lmao.ninja/v3/covid-19/all";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        // Handle the JSON object and
                        // handle it inside try and catch
                        try {


                            JSONObject jsonObject
                                    = new JSONObject(
                                    response.toString());

                            System.out.println(jsonObject);
                            tvCases.setText(
                                    jsonObject.getString(
                                            "cases"));
                            tvRecovered.setText(
                                    jsonObject.getString(
                                            "recovered"));
                            tvCritical.setText(
                                    jsonObject.getString(
                                            "critical"));
                            tvActive.setText(
                                    jsonObject.getString(
                                            "active"));
                            tvTodayCases.setText(
                                    jsonObject.getString(
                                            "todayCases"));
                            tvTotalDeaths.setText(
                                    jsonObject.getString(
                                            "deaths"));
                            tvTodayDeaths.setText(
                                    jsonObject.getString(
                                            "todayDeaths"));
                            tvAffectedCountries.setText(
                                    jsonObject.getString(
                                            "affectedCountries"));
                            nextPage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent i = new Intent(MainActivity.this,NextPage.class);
                                    startActivity(i);
                                }
                            });
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
