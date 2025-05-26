package com.example.isarakanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PropertyDetailsActivity extends AppCompatActivity {

    private ImageView backBtn, propertyImage;
    private TextView propertyName, propertyCategory, propertyRating, propertyPrice, propertyDescription;
    private Button reserveBtn;
    private BoardingHouse boardingHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        getBoardingHouseData();
        initViews();
        populateData();
        setupClickListeners();
    }

    private void getBoardingHouseData() {
        boardingHouse = getIntent().getParcelableExtra("boarding_house");
        if (boardingHouse == null) {
            finish(); // Close activity if no data received
            return;
        }
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_btn);
        propertyImage = findViewById(R.id.property_image);
        propertyName = findViewById(R.id.property_name);
        propertyCategory = findViewById(R.id.property_category);
        propertyRating = findViewById(R.id.property_rating);
        propertyPrice = findViewById(R.id.property_price);
        propertyDescription = findViewById(R.id.property_description);
        reserveBtn = findViewById(R.id.reserve_btn);
    }

    private void populateData() {
        if (boardingHouse != null) {
            propertyName.setText(boardingHouse.getName());
            propertyCategory.setText(boardingHouse.getCategory());
            propertyRating.setText(boardingHouse.getStarRating());
            propertyPrice.setText(boardingHouse.getFormattedPrice());
            propertyDescription.setText(boardingHouse.getDescription());
            propertyImage.setImageResource(boardingHouse.getImageResource());
        }
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertyDetailsActivity.this, ReservationDetailsActivity.class);
                intent.putExtra("boarding_house", boardingHouse);
                startActivity(intent);
            }
        });
    }
}