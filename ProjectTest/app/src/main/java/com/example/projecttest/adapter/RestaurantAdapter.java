package com.example.projecttest.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest.R;
import com.example.projecttest.activity.RestaurantDetailActivity;
import com.example.projecttest.model.Restaurant;
import com.squareup.picasso.Picasso;
import android.content.SharedPreferences;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context mContext;
    private List<Restaurant> listRestaurant;

    public RestaurantAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = listRestaurant.get(position);
        if (restaurant == null) {
            return;
        }
        Picasso.get()
                .load(restaurant.getImage())
                .into(holder.imageViewRestaurant);
        holder.textViewName.setText(restaurant.getRestaurantTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
            intent.putExtra("restaurantId", restaurant.getRestaurantId());

            SharedPreferences sharedPreferences = mContext.getSharedPreferences("RestaurantId", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("restaurantId", restaurant.getRestaurantId());
            editor.apply();

            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (listRestaurant != null) {
            return listRestaurant.size();
        }
        return 0;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewRestaurant;
        private TextView textViewName;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRestaurant = itemView.findViewById(R.id.imageViewRetaurent);
            textViewName = itemView.findViewById(R.id.textNameRestaurant);
        }
    }
}

