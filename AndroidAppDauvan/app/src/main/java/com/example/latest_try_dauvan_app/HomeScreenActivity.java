package com.example.latest_try_dauvan_app;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class HomeScreenActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout; //this is also in the activity_main.xml file at the top with androidx (this opens the menu design)
    private NavigationView navigationView; //in activity_main.xml file )navigates to different menu buttons)

    private ImageButton menuButton; //in activity_main_content.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_transition_screen);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        menuButton = findViewById(R.id.menu_button);

        Button vehicleButton = findViewById(R.id.vehicleService);
        Button transportationButton = findViewById(R.id.transportationService);


        // Retrieve session
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);



        if (userId == null) {
            // No session found, redirect to SignInActivity
            Intent intent = new Intent(HomeScreenActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Session found, proceed with the user's data
            String userName = sharedPreferences.getString("name", "User");
            String userEmail = sharedPreferences.getString("email", "example@gmail.com");

            // Access header view in the navigation drawer
            View headerView = navigationView.getHeaderView(0);
            TextView textUserName = headerView.findViewById(R.id.textUserName); // Ensure IDs match your drawer_header layout
            TextView textUserEmail = headerView.findViewById(R.id.textUserEmail);

            // Set user name and email
            textUserName.setText(userName);
            textUserEmail.setText(userEmail);

            Toast.makeText(this, "Welcome back, " + userName, Toast.LENGTH_SHORT).show();
        }




        vehicleButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeScreenActivity.this, MainVehicleScreen.class);
            startActivity(intent);
        });

        transportationButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeScreenActivity.this, MainTransportationScreen.class);
            startActivity(intent);
        });



        // Toggle navigation drawer open/close on button click
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                if (itemId == R.id.navAboutUs) {
                    Intent intent = new Intent(HomeScreenActivity.this, DauvanContactScreen.class);
                    startActivity(intent);
                    //Optional Toast message, but add it "after" the fragment is replaced
                    Toast.makeText(HomeScreenActivity.this, "About Us Clicked", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.navLogout) {
                    Toast.makeText(HomeScreenActivity.this, "Logout Clicked", Toast.LENGTH_SHORT).show();

                }else if (itemId == R.id.navNotifications){
                    Intent intent = new Intent(HomeScreenActivity.this, NotificationsActivity.class);
                    startActivity(intent);
                    Toast.makeText(HomeScreenActivity.this, "Notifications Clicked", Toast.LENGTH_SHORT).show();

                }else if (itemId == R.id.navAccountHistory){
                    Intent intent = new Intent(HomeScreenActivity.this, ViewHistoryActivity.class);
                    startActivity(intent);
                    Toast.makeText(HomeScreenActivity.this, "Account History Clicked", Toast.LENGTH_SHORT).show();

                }else if (itemId == R.id.navAccount) {
                    Intent intent = new Intent(HomeScreenActivity.this, EditAccountActivity.class);
                    startActivity(intent);
                    Toast.makeText(HomeScreenActivity.this, "Account Clicked", Toast.LENGTH_SHORT).show();
                }else if (itemId == R.id.navCalender) {
                    Intent intent = new Intent(HomeScreenActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    Toast.makeText(HomeScreenActivity.this, "Account Clicked", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }
}






