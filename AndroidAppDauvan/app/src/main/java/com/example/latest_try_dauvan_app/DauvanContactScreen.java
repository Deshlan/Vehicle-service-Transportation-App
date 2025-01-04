package com.example.latest_try_dauvan_app;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class DauvanContactScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dauvan_contact_information);

        //google maps url address of dauvan
        TextView dauvanAddresText = findViewById(R.id.dauvanAddress);
        dauvanAddresText.setMovementMethod(LinkMovementMethod.getInstance());

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(DauvanContactScreen.this, HomeScreenActivity.class);
            startActivity(intent);
        });
    }

}
