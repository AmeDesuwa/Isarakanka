package com.example.isarakanka;

import android.os.Parcel;
import android.os.Parcelable;

public class BoardingHouse implements Parcelable {
    private int id;
    private String name;
    private String category;
    private double price;
    private float rating;
    private int imageResource;
    private String description;
    private boolean isAvailable;

    // Constructor
    public BoardingHouse(int id, String name, String category, double price,
                         float rating, int imageResource, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
        this.description = description;
        this.isAvailable = true;
    }

    // Parcelable implementation
    protected BoardingHouse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        price = in.readDouble();
        rating = in.readFloat();
        imageResource = in.readInt();
        description = in.readString();
        isAvailable = in.readByte() != 0;
    }

    public static final Creator<BoardingHouse> CREATOR = new Creator<BoardingHouse>() {
        @Override
        public BoardingHouse createFromParcel(Parcel in) {
            return new BoardingHouse(in);
        }

        @Override
        public BoardingHouse[] newArray(int size) {
            return new BoardingHouse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeDouble(price);
        dest.writeFloat(rating);
        dest.writeInt(imageResource);
        dest.writeString(description);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getFormattedPrice() {
        return "₱" + String.format("%.0f", price);
    }

    public String getStarRating() {
        StringBuilder stars = new StringBuilder();
        int fullStars = (int) rating;
        for (int i = 0; i < 5; i++) {
            if (i < fullStars) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }
}