package com.example.boardinghouse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CancelReasonActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText reasonEditText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reason);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_btn);
        reasonEditText = findViewById(R.id.reason_edit_text);
        sendBtn = findViewById(R.id.send_btn);
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = reasonEditText.getText().toString().trim();
                if (reason.isEmpty()) {
                    Toast.makeText(CancelReasonActivity.this, "Please enter a reason for cancellation", Toast.LENGTH_SHORT).show();
                } else {
                    // In a real app, you would send this to your backend
                    Toast.makeText(CancelReasonActivity.this, "Cancellation request sent successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Go back to previous screen
                }
            }
        });
    }
}