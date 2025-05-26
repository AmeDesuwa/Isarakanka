package com.example.isarakanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationsActivity extends AppCompatActivity {

    private ImageView backBtn;
    private TextView detailsTab, specificationsTab;
    private Button cancelReservationBtn;
    private boolean isDetailsSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        initViews();
        setupClickListeners();
        updateTabSelection();
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_btn);
        detailsTab = findViewById(R.id.details_tab);
        specificationsTab = findViewById(R.id.specifications_tab);
        cancelReservationBtn = findViewById(R.id.cancel_reservation_btn);
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        detailsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDetailsSelected = true;
                updateTabSelection();
            }
        });

        specificationsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDetailsSelected = false;
                updateTabSelection();
            }
        });

        cancelReservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationsActivity.this, CancelReasonActivity.class));
            }
        });
    }

    private void updateTabSelection() {
        if (isDetailsSelected) {
            detailsTab.setTextColor(getResources().getColor(R.color.purple_primary));
            specificationsTab.setTextColor(getResources().getColor(R.color.text_gray));
            detailsTab.setBackground(getResources().getDrawable(R.drawable.tab_selected_bg));
            specificationsTab.setBackground(getResources().getDrawable(R.drawable.tab_unselected_bg));
        } else {
            detailsTab.setTextColor(getResources().getColor(R.color.text_gray));
            specificationsTab.setTextColor(getResources().getColor(R.color.purple_primary));
            detailsTab.setBackground(getResources().getDrawable(R.drawable.tab_unselected_bg));
            specificationsTab.setBackground(getResources().getDrawable(R.drawable.tab_selected_bg));
        }
    }
}