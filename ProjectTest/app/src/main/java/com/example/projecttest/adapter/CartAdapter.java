package com.example.projecttest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecttest.R;
import com.example.projecttest.activity.CartActivity;
import com.example.projecttest.model.GioHang;
import com.example.projecttest.utils.GioHangManager;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context mContext;
    private List<GioHang> cartItems;
    private GioHangManager gioHangManager;

    public CartAdapter(Context mContext) {
        this.mContext = mContext;
        this.gioHangManager = new GioHangManager(mContext);
    }

    public void setData(List<GioHang> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GioHang cartItem = cartItems.get(position);
        if (cartItem == null) return;

        double totalPrice = cartItem.getPrice() * cartItem.getQuantity();
        holder.textViewFoodName.setText(cartItem.getProductName());
        String formattedPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"))
                .format(totalPrice);
        holder.textViewPrice.setText(formattedPrice);
        holder.edittextquantity.setText(String.valueOf(cartItem.getQuantity()));
        Picasso.get().load(cartItem.getImageUrl()).into(holder.imageViewFood);

        holder.buttonRemove.setOnClickListener(v -> {
            gioHangManager.removeFromCart(cartItem.getProductId());
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            ((CartActivity) mContext).calculateTotalPrice(cartItems);
        });

        holder.edittextquantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // You can use this method if you need to do something before the text changes
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // You can use this method to do something as the text changes
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newQuantityText = editable.toString();
                if (!newQuantityText.isEmpty()) {
                    int newQuantity = Integer.parseInt(newQuantityText);
                    cartItem.setQuantity(newQuantity);
                    double updatedPrice = cartItem.getPrice() * newQuantity;
                    String formattedUpdatedPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"))
                            .format(updatedPrice);
                    holder.textViewPrice.setText(formattedUpdatedPrice);
                    gioHangManager.updateQuantityInCart(cartItem.getProductId(), newQuantity);
                    ((CartActivity) mContext).calculateTotalPrice(cartItems);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return (cartItems != null) ? cartItems.size() : 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewFoodName, textViewPrice , totalPriceTextView;
        private EditText edittextquantity;
        private ImageView imageViewFood;
        private Button buttonRemove;;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            edittextquantity = itemView.findViewById(R.id.editTextQuantity);
            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}
