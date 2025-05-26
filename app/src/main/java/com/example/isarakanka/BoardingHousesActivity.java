package com.example.isarakanka;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isarakanka.BoardingHouse;

import java.util.ArrayList;
import java.util.List;

public class BoardingHousesActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText searchEditText;
    private RecyclerView boardingHousesRecyclerView;
    private BoardingHouseAdapter adapter;
    private List<BoardingHouse> boardingHousesList;
    private List<BoardingHouse> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_houses);

        initViews();
        setupRecyclerView();
        loadBoardingHouses();
        setupClickListeners();
        setupSearch();
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_btn);
        searchEditText = findViewById(R.id.search_edit_text);
        boardingHousesRecyclerView = findViewById(R.id.boarding_houses_recycler_view);
    }

    private void setupRecyclerView() {
        boardingHousesList = new ArrayList<>();
        filteredList = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        boardingHousesRecyclerView.setLayoutManager(layoutManager);

        adapter = new BoardingHouseAdapter(this, filteredList);
        boardingHousesRecyclerView.setAdapter(adapter);
    }

    private void loadBoardingHouses() {
        // Sample data - In real app, this would come from API or database
        boardingHousesList.add(new BoardingHouse(
                1,
                "Comfy Sofa",
                "Sofas",
                1000.0,
                4.0f,
                R.drawable.sample_room_1,
                "Welcome to Sunny Stay Boarding House, your cozy home away from home. Conveniently located in a quiet neighborhood just minutes away from downtown."
        ));

        boardingHousesList.add(new BoardingHouse(
                2,
                "Top Sofa",
                "Sofas",
                800.0,
                3.0f,
                R.drawable.sample_room_2,
                "Modern boarding house with excellent amenities and comfortable living spaces."
        ));

        boardingHousesList.add(new BoardingHouse(
                3,
                "Premium Room",
                "Private Rooms",
                1500.0,
                5.0f,
                R.drawable.sample_room_3,
                "Luxury boarding house with private bathrooms and premium facilities."
        ));

        boardingHousesList.add(new BoardingHouse(
                4,
                "Budget Stay",
                "Shared Rooms",
                600.0,
                3.5f,
                R.drawable.sample_room_4,
                "Affordable boarding house perfect for students and budget travelers."
        ));

        filteredList.addAll(boardingHousesList);
        adapter.notifyDataSetChanged();
    }

    private void setupClickListeners() {
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBoardingHouses(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterBoardingHouses(String query) {
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(boardingHousesList);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (BoardingHouse house : boardingHousesList) {
                if (house.getName().toLowerCase().contains(lowerCaseQuery) ||
                        house.getCategory().toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(house);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}