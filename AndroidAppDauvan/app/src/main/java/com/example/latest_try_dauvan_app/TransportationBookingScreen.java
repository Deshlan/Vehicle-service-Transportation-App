package com.example.latest_try_dauvan_app;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.text.method.PasswordTransformationMethod;
import android.widget.ImageButton;
import android.view.View;

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

public class TransportationBookingScreen extends AppCompatActivity {

    private EditText items_type_input, weight_input, size_input, pickup_input, dropoff_input, date_input;
    private Button request_quote_btn;

    private String URL = "http://10.0.2.2/dauvanDB/transportation.php";
    private String items_type, weight, size, pickup, dropoff, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transportation_booking_screen);

        items_type_input = findViewById(R.id.typeOfGoodsInput);
        weight_input = findViewById(R.id.weightInput);
        size_input = findViewById(R.id.sizeInput);
        pickup_input = findViewById(R.id.pickUpAddress);
        dropoff_input = findViewById(R.id.dropOffAddress);
        date_input = findViewById(R.id.dateDetails);

        request_quote_btn = findViewById(R.id.request_service_button);

        items_type = weight = size = pickup = dropoff = date = "";


        request_quote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items_type = items_type_input.getText().toString().trim();
                weight = weight_input.getText().toString().trim();
                size = size_input.getText().toString().trim();
                pickup = pickup_input.getText().toString().trim();
                dropoff = dropoff_input.getText().toString().trim();
                date = date_input.getText().toString().trim();

                if (items_type.isEmpty()) {
                    items_type_input.setError("Goods type input is required");
                } else if (weight.isEmpty()) {
                    weight_input.setError("Weight input is required");
                } else if (size.isEmpty()) {
                    size_input.setError("Size input is required");
                } else if (pickup.isEmpty()) {
                    pickup_input.setError("Pickup address input is required");
                } else if (dropoff.isEmpty()) {
                    dropoff_input.setError("Dropoff address input is required");
                } else if (date.isEmpty()) {
                    date_input.setError("Date input is required");
                } else {
                    // Call the transportation method
                    transportation(view);
                }
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TransportationBookingScreen.this, MainTransportationScreen.class);
            startActivity(intent);
        });
    }

    // Transportation method to handle user transport input with MySQL and Volley
    public void transportation(View view) {
        items_type = items_type_input.getText().toString().trim();
        weight = weight_input.getText().toString().trim();
        size = size_input.getText().toString().trim();
        pickup = pickup_input.getText().toString().trim();
        dropoff = dropoff_input.getText().toString().trim();
        date = date_input.getText().toString().trim();

        // Retrieve session
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        if (!items_type.equals("") && !weight.equals("") && !size.equals("") && !pickup.equals("") && !dropoff.equals("") && !date.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        // Show success message
                        Toast.makeText(TransportationBookingScreen.this, "Request quote successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to HomeMainScreen
                        Intent intent = new Intent(TransportationBookingScreen.this, MainTransportationScreen.class);
                        startActivity(intent);
                        finish(); // Close the current screen
                    } else if (response.equals("failure")) {
                        // Show failure message
                        Toast.makeText(TransportationBookingScreen.this, "Failed to request quote. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle Volley error
                    Toast.makeText(TransportationBookingScreen.this, "Transport Booking Failed", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("items", items_type);
                    data.put("weight", weight);
                    data.put("size", size);
                    data.put("pickup", pickup);
                    data.put("dropoff", dropoff);
                    data.put("scheduled_date", date);
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
