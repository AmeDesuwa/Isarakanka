package com.example.isarakanka;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationDetailsActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText peopleCountEditText;
    private TextView amountTextView;
    private Button cashBtn, cardBtn, finishBtn;
    private BoardingHouse boardingHouse;
    private boolean isCashSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        getBoardingHouseData();
        initViews();
        setupClickListeners();
        updatePaymentSelection();
        updateAmount();
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
        peopleCountEditText = findViewById(R.id.people_count_edit_text);
        amountTextView = findViewById(R.id.amount_text_view);
        cashBtn = findViewById(R.id.cash_btn);
        cardBtn = findViewById(R.id.card_btn);
        finishBtn = findViewById(R.id.finish_btn);
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCashSelected = true;
                updatePaymentSelection();
            }
        });

        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCashSelected = false;
                updatePaymentSelection();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeReservation();
            }
        });
    }

    private void updatePaymentSelection() {
        if (isCashSelected) {
            cashBtn.setBackgroundResource(R.drawable.button_purple);
            cashBtn.setTextColor(getResources().getColor(android.R.color.white));
            cardBtn.setBackgroundResource(R.drawable.button_outline);
            cardBtn.setTextColor(getResources().getColor(R.color.purple_primary));
        } else {
            cashBtn.setBackgroundResource(R.drawable.button_outline);
            cashBtn.setTextColor(getResources().getColor(R.color.purple_primary));
            cardBtn.setBackgroundResource(R.drawable.button_purple);
            cardBtn.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private void updateAmount() {
        if (boardingHouse != null) {
            amountTextView.setText(boardingHouse.getFormattedPrice());
        }
    }

    private void completeReservation() {
        String peopleCount = peopleCountEditText.getText().toString().trim();

        if (peopleCount.isEmpty()) {
            Toast.makeText(this, "Please enter number of people", Toast.LENGTH_SHORT).show();
            return;
        }

        int count = Integer.parseInt(peopleCount);
        if (count <= 0) {
            Toast.makeText(this, "Number of people must be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        // In a real app, you would process the reservation here
        String paymentMethod = isCashSelected ? "Cash" : "Card";
        String message = "Reservation confirmed!\nPeople: " + count + "\nPayment: " + paymentMethod;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Navigate back to main activity
        finish();
    }
}