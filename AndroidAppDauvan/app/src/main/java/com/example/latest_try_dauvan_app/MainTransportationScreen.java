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

public class MainTransportationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_transportation_service_screen);

        Button bookTransportButton = findViewById(R.id.bookTransportationButton);
        Button trackTransportButton = findViewById(R.id.trackTransportationButton);
        ImageButton backButton = findViewById(R.id.back_button);


        bookTransportButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainTransportationScreen.this, TransportationBookingScreen.class);
            startActivity(intent);
        });

        trackTransportButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainTransportationScreen.this, TransportationTrackingScreen.class);
            startActivity(intent);

        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainTransportationScreen.this, HomeScreenActivity.class);
            startActivity(intent);
        });
    }
}
