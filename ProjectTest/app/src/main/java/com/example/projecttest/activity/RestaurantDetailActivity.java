package com.example.projecttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecttest.R;
import com.example.projecttest.adapter.CategoryAdapter;
import com.example.projecttest.model.Category;
import com.example.projecttest.model.Food;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {
    private ImageView restaurantImageView;
    private TextView restaurantNameTextView;
    private TextView restaurantDescriptionTextView , restaurant_rating , txtopendate;
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_detail);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chi tiết nhà hàng");
        }


        // Initialize views
        restaurantImageView = findViewById(R.id.restaurant_image);
        restaurantNameTextView = findViewById(R.id.restaurant_name);
        restaurantDescriptionTextView = findViewById(R.id.restaurant_description);
        restaurant_rating = findViewById(R.id.restaurant_rating);
        txtopendate = findViewById(R.id.opendate);
        int restaurantId = getIntent().getIntExtra("restaurantId", -1);

        rcvCategory = findViewById(R.id.rcvCategory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCategory.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this);

        if (restaurantId != -1) {
            fetchRestaurantDetails(restaurantId);
        } else {
            Toast.makeText(this, "Invalid Restaurant ID", Toast.LENGTH_SHORT).show();
        }
    }
    private void fetchRestaurantDetails(int restaurantId) {
        // Define the API endpoint URL
        String url = "http://192.168.2.33:8081/api/v1/restaurant/details?id=" + restaurantId;

        RequestQueue queue = Volley.newRequestQueue(this);
        List<Category> categoryList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the response
                            JSONObject restaurantData = response.getJSONObject("data");

                            // Set restaurant details
                            String name = restaurantData.getString("restaurantTitle"); // Make sure key matches
                            String imageUrl = restaurantData.getString("image");
                            String description = restaurantData.getString("restaurantDes"); // Make sure key matches
                            String openDate = restaurantData.getString("openDate"); // Make sure key matches
                            Double rating = restaurantData.getDouble("rating");
                            Picasso.get().load(imageUrl).into(restaurantImageView);
                            restaurantNameTextView.setText(name);
                            restaurantDescriptionTextView.setText(description);
                            restaurant_rating.setText(rating.toString());

                            // Parse categories
                            JSONArray categoryArray = restaurantData.getJSONArray("categories");
                            List<Category> categoryList = new ArrayList<>();
                            for (int i = 0; i < categoryArray.length(); i++) {
                                JSONObject categoryObject = categoryArray.getJSONObject(i);
                                String categoryName = categoryObject.getString("nameCategory"); // Category name field

                                // Parse food items within this category
                                JSONArray menuArray = categoryObject.getJSONArray("menuDTOList");
                                List<Food> foodList = new ArrayList<>();
                                for (int j = 0; j < menuArray.length(); j++) {
                                    JSONObject foodObject = menuArray.getJSONObject(j);
                                    String foodName = foodObject.getString("foodName");
                                    String foodImage = foodObject.getString("foodImage");
                                    int isFreeShip = foodObject.getInt("isFreeShip");
                                    double price = foodObject.getDouble("foodPrice");
                                    int id = foodObject.getInt("foodId");
                                    foodList.add(new Food(id ,foodName, foodImage, isFreeShip , price));
                                }

                                // Add category to list
                                categoryList.add(new Category(categoryName, foodList));
                            }

                            // Set data for adapter
                            categoryAdapter.setData(categoryList);
                            rcvCategory.setAdapter(categoryAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RestaurantDetailActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle network error
                        Log.e("RestaurantDetail", "Error fetching data: " + error.getMessage());
                        Toast.makeText(RestaurantDetailActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the Volley request queue
        queue.add(jsonObjectRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
