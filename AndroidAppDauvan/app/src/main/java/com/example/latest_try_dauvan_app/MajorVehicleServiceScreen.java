package com.example.latest_try_dauvan_app;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.widget.Button;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// volley imports
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;
import java.util.HashMap;

public class MajorVehicleServiceScreen extends AppCompatActivity {

    private EditText make_details_input, model_details_input, year_details_input, vin_num_input, date_input, description_input;
    private Button request_quote_btn;

    private String URL = "http://10.0.2.2/dauvanDB/major_vehicle_services.php";
    private String make_details, model_details, year_details, vin_num, date, issue_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_major_service_screen);

        make_details_input = findViewById(R.id.makeDetails);
        model_details_input = findViewById(R.id.modelDetails);
        year_details_input = findViewById(R.id.yearMake);
        vin_num_input = findViewById(R.id.vinDetails);
        date_input = findViewById(R.id.dateDetails);
        description_input = findViewById(R.id.majorDescriptionDetails);

        request_quote_btn = findViewById(R.id.request_service_button);

        make_details = model_details = year_details = vin_num = date = issue_description = "";

        request_quote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_details = make_details_input.getText().toString().trim();
                model_details = model_details_input.getText().toString().trim();
                year_details = year_details_input.getText().toString().trim();
                vin_num = vin_num_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                issue_description = description_input.getText().toString().trim();

                if (make_details.isEmpty()) {
                    make_details_input.setError("Car make is required");
                } else if (model_details.isEmpty()) {
                    model_details_input.setError("car model is required");
                } else if (year_details.isEmpty()) {
                    year_details_input.setError("Car year is required");
                } else if (vin_num.isEmpty()) {
                    vin_num_input.setError("The car's VIN number input is required");
                } else if (date.isEmpty()) {
                    date_input.setError("Date input is required");
                } else if (issue_description.isEmpty()) {
                    description_input.setError("Issue description is required");
                } else {
                    // Call the major vehicle service method
                    major_vehicle_service(view);
                }
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(MajorVehicleServiceScreen.this, MainVehicleScreen.class);
            startActivity(intent);
        });
    }

    // vehicle_service method to handle user vehicle input with MySQL and Volley
    public void major_vehicle_service(View view) {
        make_details = make_details_input.getText().toString().trim();
        model_details = model_details_input.getText().toString().trim();
        year_details = year_details_input.getText().toString().trim();
        vin_num = vin_num_input.getText().toString().trim();
        date = date_input.getText().toString().trim();
        issue_description = description_input.getText().toString().trim();

        // Retrieve session
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);


        if (!model_details.equals("") && !make_details.equals("") && !year_details.equals("") && !vin_num.equals("") && !date.equals("") && !issue_description.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        // Show success message
                        Toast.makeText(MajorVehicleServiceScreen.this, "Request quote successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to HomeMainScreen
                        Intent intent = new Intent(MajorVehicleServiceScreen.this, MainVehicleScreen.class);
                        startActivity(intent);
                        finish(); // Close the current screen
                    } else if (response.equals("failure")) {
                        // Show failure message
                        Toast.makeText(MajorVehicleServiceScreen.this, "Failed to request quote. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle Volley error
                    Toast.makeText(MajorVehicleServiceScreen.this, "Normal Vehicle Service Failed", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("vehicle_make", make_details);
                    data.put("vehicle_model", model_details);
                    data.put("vehicle_year", year_details);
                    data.put("vin_number", vin_num);
                    data.put("scheduled_date", date);
                    data.put("issue_description", issue_description);
                    data.put("user_id", userId);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            // Show message if fields are empty
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }

    }
}
