package com.example.isarakanka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText fullnameEditText, addressEditText, contactEditText, dobEditText, emailEditText;
    private Button doneBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_btn);
        fullnameEditText = findViewById(R.id.fullname_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        contactEditText = findViewById(R.id.contact_edit_text);
        dobEditText = findViewById(R.id.dob_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        doneBtn = findViewById(R.id.done_btn);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePersonalInfo();
            }
        });
    }

    private void savePersonalInfo() {
        String fullname = fullnameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String contact = contactEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (fullname.isEmpty()) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", fullname);
        editor.putString("address", address);
        editor.putString("contact", contact);
        editor.putString("dob", dob);
        editor.putString("email", email);
        editor.putBoolean("is_logged_in", true);
        editor.apply();

        Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();

        // Navigate to main activity - Fixed the reference
        Intent intent = new Intent(PersonalInfoActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}