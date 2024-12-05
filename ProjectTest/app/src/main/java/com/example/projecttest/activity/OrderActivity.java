package com.example.projecttest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecttest.R;
import com.example.projecttest.adapter.CartAdapter;
import com.example.projecttest.adapter.OrderAdapter;
import com.example.projecttest.model.GioHang;
import com.example.projecttest.model.Order;
import com.example.projecttest.model.Shipping;
import com.example.projecttest.utils.GioHangManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    private OrderAdapter orderAdapter;
    private RecyclerView recyclerViewCart;
    private TextView textViewTotalPrice , etName , etPhone , etAddress;
    private GioHangManager gioHangManager;
    private Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        recyclerViewCart = findViewById(R.id.rcvCart);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);

        btnPay = findViewById(R.id.btnPay);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Thông tin nhận hàng");
        }

        gioHangManager = new GioHangManager(this);
        List<GioHang> cart = gioHangManager.getCart();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewCart.setLayoutManager(gridLayoutManager);
        orderAdapter = new OrderAdapter(this);
        orderAdapter.setData(cart);
        recyclerViewCart.setAdapter(orderAdapter);
        textViewTotalPrice.setText(gioHangManager.calculateTotalPrice(cart));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order();
            }
        });
    }
    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void order() {
        if (!validateInputs()) return;

        String url = "http://192.168.2.33:8081/api/v1/order/";

        // Get userId and restaurantId from SharedPreferences
        SharedPreferences userPrefs = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        int userId = userPrefs.getInt("userID", -1);

        SharedPreferences restaurantPrefs = getSharedPreferences("RestaurantId", MODE_PRIVATE);
        int restaurantId = restaurantPrefs.getInt("restaurantId", -1);

        // Build the Order object
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);

        // Set shipping details
        Shipping shipping = new Shipping();
        shipping.setAddress(etAddress.getText().toString().trim());
        shipping.setMobile(etPhone.getText().toString().trim());
        shipping.setUsername(etName.getText().toString().trim());
        order.setShipping(shipping);

        // Set the cart items
        List<GioHang> cart = gioHangManager.getCart();
        order.setFoodOrderRequests(cart);

        // Convert Order object to JSON
        Gson gson = new GsonBuilder().create();
        String orderJson = gson.toJson(order);

        try {
            JSONObject orderData = new JSONObject(orderJson);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, orderData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(OrderActivity.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                            gioHangManager.clearCart();
                            Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OrderActivity.this, "Lỗi khi đặt hàng. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(OrderActivity.this, "Lỗi khi tạo đơn hàng. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(OrderActivity.this, CartActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}