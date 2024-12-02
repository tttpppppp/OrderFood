package com.example.projecttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest.R;
import com.example.projecttest.activity.FoodDetails;
import com.example.projecttest.activity.RestaurantDetailActivity;
import com.example.projecttest.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context mContext;
    private List<Food> listFood;

    public FoodAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Food> listFood) {
        this.listFood = listFood;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = listFood.get(position);
        if (food == null) {
            return;
        }

        Picasso.get()
                .load(food.getFoodImage())
                .into(holder.imageViewFood);

        holder.textViewFoodName.setText(food.getFoodName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, FoodDetails.class);
            intent.putExtra("foodObject", food);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (listFood != null) {
            return listFood.size();
        }
        return 0;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewFood;
        private TextView textViewFoodName;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageFood);
            textViewFoodName = itemView.findViewById(R.id.txtnamefood);
        }
    }
}
