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
import com.example.projecttest.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context mContext;
    private List<GioHang> gioHangs;

    public OrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<GioHang> listOrder) {
        this.gioHangs = listOrder;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        GioHang orderItem = gioHangs.get(position);
        if (orderItem == null) {
            return;
        }

        // Load the image
        Picasso.get()
                .load(orderItem.getImageUrl())
                .into(holder.imageViewFood);

        // Set name and formatted price
        holder.textViewFoodName.setText(orderItem.getProductName());
        String formattedPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"))
                .format(orderItem.getPrice());
        holder.txtPrice.setText(formattedPrice);
        holder.txtQuantity.setText(String.valueOf(orderItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return gioHangs != null ? gioHangs.size() : 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewFood;
        private TextView textViewFoodName, txtPrice , txtQuantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageFood);
            textViewFoodName = itemView.findViewById(R.id.txtnamefood);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}
