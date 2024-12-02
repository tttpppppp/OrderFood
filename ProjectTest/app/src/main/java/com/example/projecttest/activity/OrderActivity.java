package com.example.projecttest.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest.R;
import com.example.projecttest.adapter.CartAdapter;
import com.example.projecttest.model.GioHang;
import com.example.projecttest.utils.GioHangManager;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private RecyclerView recyclerViewCart;
    private TextView textViewTotalPrice;
    private GioHangManager gioHangManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        recyclerViewCart = findViewById(R.id.rcvCart);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Giỏ hàng");
        }

        gioHangManager = new GioHangManager(this);
        List<GioHang> cart = gioHangManager.getCart();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewCart.setLayoutManager(gridLayoutManager);
        cartAdapter = new CartAdapter(this);
        cartAdapter.setData(cart);
        recyclerViewCart.setAdapter(cartAdapter);
    }
}