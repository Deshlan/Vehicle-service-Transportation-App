package com.example.latest_try_dauvan_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private EditText email_input, password_input;
    private Button signin_btn_screen;
    private TextView no_account_text;
    private CheckBox checkBox;

    private String email, password;
    private final String URL = "http://10.0.2.2/dauvanDB/login.php"; // Localhost URL for emulator testing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize UI components
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        signin_btn_screen = findViewById(R.id.signin_btn_screen);
        no_account_text = findViewById(R.id.no_account_text);
        checkBox = findViewById(R.id.checkBox);

        // Set up button click listeners
        signin_btn_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getText().toString().trim();
                password = password_input.getText().toString().trim();

                if (validateInputs()) {
                    login();
                }
            }
        });

        no_account_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the registration screen
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            }
        });

        // Show/Hide password using the checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password_input.setTransformationMethod(null); // Show password
                } else {
                    password_input.setTransformationMethod(PasswordTransformationMethod.getInstance()); // Hide password
                }
            }
        });
    }

    // Validate user inputs for email and password
    private boolean validateInputs() {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_input.setError("Enter a valid email address");
            return false;
        }
        if (password.isEmpty()) {
            password_input.setError("Password is required");
            return false;
        }
        return true;
    }

    // Login function using Volley for HTTP requests
    private void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("SignInResponse", response); // Log server response for debugging
                        try {
                            // Parse JSON response from the server
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                // Extract user details from the response
                                String userId = jsonObject.getString("user_id");
                                String userName = jsonObject.getString("name");
                                String userEmail = jsonObject.getString("email");

                                // Save session details in SharedPreferences
                                saveSession(userId, userName, userEmail);

                                // Navigate to HomeScreenActivity
                                Intent intent = new Intent(SignInActivity.this, HomeScreenActivity.class);
                                startActivity(intent);
                                finish(); // Prevent back navigation to SignInActivity
                            } else {
                                Toast.makeText(SignInActivity.this, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(SignInActivity.this, "Error parsing response.", Toast.LENGTH_SHORT).show();
                            Log.e("SignInError", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display error message if the request fails
                        Toast.makeText(SignInActivity.this, "Unable to connect. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("SignInError", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Pass email and password as POST parameters
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);
                return data;
            }
        };

        // Add the request to the Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void saveSession(String userId, String userName, String userEmail) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", userId);
        editor.putString("name", userName);
        editor.putString("email", userEmail);
        editor.apply(); // Save changes
    }
}
