package com.example.latest_try_dauvan_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainVehicleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vehicle_service_screen);

        Button normalServiceButton = findViewById(R.id.normalServiceButton);
        Button majorServiceButton = findViewById(R.id.majorServiceButton);
        ImageButton backButton = findViewById(R.id.back_button);


        normalServiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainVehicleScreen.this, NormalVehicleServiceScreen.class);
            startActivity(intent);
        });

        majorServiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainVehicleScreen.this, MajorVehicleServiceScreen.class);
            startActivity(intent);

        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainVehicleScreen.this, HomeScreenActivity.class);
            startActivity(intent);
        });
    }
}
