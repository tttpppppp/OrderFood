package com.example.projecttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest.R;
import com.example.projecttest.adapter.CartAdapter;
import com.example.projecttest.model.GioHang;
import com.example.projecttest.utils.GioHangManager;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private TextView textViewTotalPrice , txtcartemty;
    private CartAdapter cartAdapter;
    private Button btnCheckout, btnClearAll;
    private GioHangManager gioHangManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.rcvCart);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnClearAll = findViewById(R.id.btnClearAll);
        txtcartemty = findViewById(R.id.txtcartemty);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Giỏ hàng");
        }
        // Initialize GioHangManager
        gioHangManager = new GioHangManager(this);
        List<GioHang> cart = gioHangManager.getCart();

        if (cart.isEmpty()) {
            recyclerViewCart.setVisibility(View.GONE); // Ẩn RecyclerView
            txtcartemty.setVisibility(View.VISIBLE); // Hiển thị thông báo giỏ hàng trống
        } else {
            recyclerViewCart.setVisibility(View.VISIBLE); // Hiển thị RecyclerView
            txtcartemty.setVisibility(View.GONE); // Ẩn thông báo giỏ hàng trống
        }
        // Set up RecyclerView with CartAdapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCart.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this);
        cartAdapter.setData(cart);
        recyclerViewCart.setAdapter(cartAdapter);

        // Calculate total price
        calculateTotalPrice(cart);

        // Clear Cart button listener
        btnClearAll.setOnClickListener(v -> {
            gioHangManager.clearCart();
            cartAdapter.setData(gioHangManager.getCart());
            calculateTotalPrice(gioHangManager.getCart());
        });

        // Checkout button listener (currently empty)
        btnCheckout.setOnClickListener(v -> {
            // Implement checkout functionality here
        });

        Gson gson = new Gson();
        Log.d("CartJSON" , gson.toJson(cart));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Method to calculate the total price of items in the cart
    public void calculateTotalPrice(List<GioHang> cart) {
        double totalPrice = 0;
        if (cart != null && !cart.isEmpty()) {
            for (GioHang item : cart) {
                totalPrice += item.getPrice() * item.getQuantity();
            }

            // Format the total price
            String formattedTotalPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(totalPrice);
            textViewTotalPrice.setText("Tổng cộng: " + formattedTotalPrice);
        } else {
            textViewTotalPrice.setText("Tổng cộng: 0 VND");
        }
    }
}
