package com.example.latest_try_dauvan_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Volley imports
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_input, password_input, name_input, phone_input, vat_number_input;
    private Button register_btn_screen;
    private TextView have_account_text, tvStatus;

    private CheckBox checkBox;

    private String URL = "http://10.0.2.2/dauvanDB/registerscreen.php";
    private String email, password, name, phone, vat_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize UI components
        email_input = findViewById(R.id.register_email_input);
        password_input = findViewById(R.id.register_password_input);
        register_btn_screen = findViewById(R.id.register_btn_screen);
        have_account_text = findViewById(R.id.have_account_text);
        checkBox = findViewById(R.id.checkBox);

        name_input = findViewById(R.id.first_name_input);
        phone_input = findViewById(R.id.phone_number_input);
        vat_number_input = findViewById(R.id.vat_number_input);
        tvStatus = findViewById(R.id.tvStatus);

        email = name = password = phone = vat_number = "";

        // Register button click listener
        register_btn_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getText().toString().trim();
                password = password_input.getText().toString().trim();

                if (email.isEmpty()) {
                    email_input.setError("Email input is required");
                } else if (password.isEmpty()) {
                    password_input.setError("Password input is required");
                } else {
                    // Call the register method
                    register(view);
                    startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                }
            }
        });

        // Have account text click listener
        have_account_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
            }
        });

        // Show/hide password using the checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password_input.setTransformationMethod(null);
                } else {
                    password_input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    // Register method to handle user registration with MySQL and Volley
    public void register(View view) {
        name = name_input.getText().toString().trim();
        email = email_input.getText().toString().trim();
        password = password_input.getText().toString().trim();
        phone = phone_input.getText().toString().trim();
        vat_number = vat_number_input.getText().toString().trim();

        if (!name.equals("") && !email.equals("") && !password.equals("") && !phone.equals("") && !vat_number.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        register_btn_screen.setClickable(false);
                    } else if (response.equals("failure")) {
                        tvStatus.setText("Something went wrong!");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    data.put("name", name);
                    data.put("phone", phone);
                    data.put("vatNumber", vat_number);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
