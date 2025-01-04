package com.example.latest_try_dauvan_app;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class TransportationTrackingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transportation_tracking_screen);


        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TransportationTrackingScreen.this, MainTransportationScreen.class);
            startActivity(intent);
        });

    }
}
