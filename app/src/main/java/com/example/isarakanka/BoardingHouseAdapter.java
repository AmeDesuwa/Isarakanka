package com.example.isarakanka;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BoardingHouseAdapter extends RecyclerView.Adapter<BoardingHouseAdapter.ViewHolder> {

    private Context context;
    private List<BoardingHouse> boardingHousesList;

    public BoardingHouseAdapter(Context context, List<BoardingHouse> boardingHousesList) {
        this.context = context;
        this.boardingHousesList = boardingHousesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_boarding_house, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardingHouse boardingHouse = boardingHousesList.get(position);

        holder.nameTextView.setText(boardingHouse.getName());
        holder.categoryTextView.setText(boardingHouse.getCategory());
        holder.priceTextView.setText(boardingHouse.getFormattedPrice());
        holder.ratingTextView.setText(boardingHouse.getStarRating());
        holder.imageView.setImageResource(boardingHouse.getImageResource());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PropertyDetailsActivity.class);
                intent.putExtra("boarding_house", boardingHouse);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return boardingHousesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView nameTextView;
        TextView categoryTextView;
        TextView priceTextView;
        TextView ratingTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.house_image);
            nameTextView = itemView.findViewById(R.id.house_name);
            categoryTextView = itemView.findViewById(R.id.house_category);
            priceTextView = itemView.findViewById(R.id.house_price);
            ratingTextView = itemView.findViewById(R.id.house_rating);
        }
    }
}