package com.example.latest_try_dauvan_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    private EditText fnameEdit, phoneEdit, vatEdit, passwordEdit;
    private CheckBox checkBox;
    private Button edit_acc_btn;

    private final String URL = "http://10.0.2.2/dauvanDB/update_user.php";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit_account);

        // Initializing UI components
        fnameEdit = findViewById(R.id.fnameeditacc);
        phoneEdit = findViewById(R.id.editphonenum);
        vatEdit = findViewById(R.id.editvatNumber);
        passwordEdit = findViewById(R.id.editpassword);
        edit_acc_btn = findViewById(R.id.edit_acc_btn);
        checkBox = findViewById(R.id.checkBox);

        // Retrieve user session from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        if (userId == null) {
            Toast.makeText(this, "User not logged in. Please log in again.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditAccountActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Back button functionality
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(EditAccountActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        });

        // Show/hide password
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordEdit.setTransformationMethod(null);
            } else {
                passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        edit_acc_btn.setOnClickListener(view -> {
            String name = fnameEdit.getText().toString().trim();
            String phone = phoneEdit.getText().toString().trim();
            String vatNumber = vatEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();

            Map<String, String> params = new HashMap<>();
            params.put("user_id", userId); // Send the user ID to identify the account

            // Add fields that the user has filled
            if (!name.isEmpty())
                params.put("Name", name); // Matches the column name in the database
            if (!phone.isEmpty())
                params.put("phone", phone); // Matches the column name in the database
            if (!vatNumber.isEmpty())
                params.put("VATNumber", vatNumber); // Matches the column name in the database
            if (!password.isEmpty())
                params.put("password", password); // Matches the column name in the database

            // Check if no fields are filled
            if (params.size() == 1) { // Only user_id is present
                Toast.makeText(this, "Please fill at least one field to update.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send request to update user information
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    response -> {
                        Toast.makeText(this, "Account updated successfully!", Toast.LENGTH_SHORT).show();
                        // Optionally, update SharedPreferences with the new data
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (!name.isEmpty()) editor.putString("Name", name);
                        if (!phone.isEmpty()) editor.putString("phone", phone);
                        if (!vatNumber.isEmpty()) editor.putString("VATNumber", vatNumber);
                        if (!password.isEmpty()) editor.putString("password", password);
                        editor.apply();
                    },
                    error -> Toast.makeText(this, "Error updating account: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };

            startActivity(new Intent(EditAccountActivity.this, HomeScreenActivity.class));

            // Add the request to the Volley request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        });
    }
}
