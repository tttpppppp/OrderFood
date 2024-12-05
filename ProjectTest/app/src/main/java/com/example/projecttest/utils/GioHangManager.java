package com.example.projecttest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projecttest.model.GioHang;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GioHangManager {
    private SharedPreferences sharedPreferences;
    private static final String CART_KEY = "gioHang";

    public GioHangManager(Context context) {
        sharedPreferences = context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE);
    }

    public void addToCart(GioHang item) {
        List<GioHang> cart = getCart();
        cart.add(item);
        saveCart(cart);
    }

    public List<GioHang> getCart() {
        String cartString = sharedPreferences.getString(CART_KEY, "[]");
        List<GioHang> cart = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(cartString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                GioHang item = new GioHang(
                        jsonObject.getInt("productId"),
                        jsonObject.getString("productName"),
                        jsonObject.getInt("quantity"),
                        jsonObject.getDouble("price"),
                        jsonObject.getString("imageUrl")
                );
                cart.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void saveCart(List<GioHang> cart) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray();
        try {
            for (GioHang item : cart) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productId", item.getProductId());
                jsonObject.put("productName", item.getProductName());
                jsonObject.put("quantity", item.getQuantity());
                jsonObject.put("price", item.getPrice());
                jsonObject.put("imageUrl", item.getImageUrl());
                jsonArray.put(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.putString(CART_KEY, jsonArray.toString());
        editor.apply();
    }

    public void removeFromCart(int productId) {
        List<GioHang> cart = getCart();
        cart.removeIf(item -> item.getProductId() == productId);
        saveCart(cart);
    }

    public void clearCart() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_KEY);
        editor.apply();
    }

    public void updateQuantityInCart(int productId, int newQuantity) {
        List<GioHang> cart = getCart();
        for (GioHang item : cart) {
            if (item.getProductId() == productId) {
                item.setQuantity(newQuantity);
                break;
            }
        }
        saveCart(cart);
    }

    public String calculateTotalPrice(List<GioHang> cart) {
        double totalPrice = 0;
        if (cart != null && !cart.isEmpty()) {
            for (GioHang item : cart) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
            String formattedTotalPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(totalPrice);
            return formattedTotalPrice;
        }
        return "";
    }
}
