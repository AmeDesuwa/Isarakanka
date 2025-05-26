package com.example.isarakanka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView profileName, profileUsername;
    private View viewReservationsLayout;
    private Button signOutBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListeners();
        loadUserData();
    }

    private void initViews() {
        profileName = findViewById(R.id.profile_name);
        profileUsername = findViewById(R.id.profile_username);
        viewReservationsLayout = findViewById(R.id.view_reservations_layout);
        signOutBtn = findViewById(R.id.sign_out_btn);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
    }

    private void setupClickListeners() {
        viewReservationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReservationsActivity.class));
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void loadUserData() {
        String name = sharedPreferences.getString("user_name", "John Smith");
        String username = sharedPreferences.getString("username", "@smith");

        profileName.setText(name);
        profileUsername.setText(username);
    }

    private void signOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}