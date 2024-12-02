package com.example.projecttest.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecttest.R;
import com.example.projecttest.adapter.CategoryAdapter;
import com.example.projecttest.adapter.RestaurantAdapter;
import com.example.projecttest.model.Category;
import com.example.projecttest.model.Food;
import com.example.projecttest.model.Restaurant;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private RecyclerView rcvRestaurant;
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private RestaurantAdapter mRestaurantAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        ActionViewFlipper();
        viewFlipper.setInAnimation(getContext(), R.anim.flip_in);
        viewFlipper.setOutAnimation(getContext(), R.anim.flip_out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();

        rcvRestaurant = rootView.findViewById(R.id.rcvRestaurent);
        mRestaurantAdapter = new RestaurantAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rcvRestaurant.setLayoutManager(gridLayoutManager);
        listRestaurent();

        rcvCategory = rootView.findViewById(R.id.rcvCategory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvCategory.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext());
        listCategories();
        return rootView;
    }
    public void listCategories() {
        List<Category> categoryList = new ArrayList<>();
        String url = "http://192.168.2.33:8081/api/v1/category/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("status") == 200) {
                                JSONArray categoryArray = response.getJSONArray("data");

                                for (int i = 0; i < categoryArray.length(); i++) {
                                    JSONObject categoryObject = categoryArray.getJSONObject(i);
                                    String categoryName = categoryObject.getString("nameCategory");
                                    JSONArray menuArray = categoryObject.getJSONArray("menuDTOList");
                                    List<Food> foodList = new ArrayList<>();
                                    for (int j = 0; j < menuArray.length(); j++) {
                                        JSONObject foodObject = menuArray.getJSONObject(j);
                                        String foodName = foodObject.getString("foodName");
                                        String foodImage = foodObject.getString("foodImage");
                                        int isFreeShip = foodObject.getInt("isFreeShip");
                                        double price = foodObject.getDouble("foodPrice");
                                        int id = foodObject.getInt("foodId");
                                        foodList.add(new Food(id ,foodName, foodImage, isFreeShip ,price));
                                    }

                                    categoryList.add(new Category(categoryName, foodList));
                                }
                                categoryAdapter.setData(categoryList);
                                rcvCategory.setAdapter(categoryAdapter);

                            } else {
                                Toast.makeText(getContext(), "Failed to load categories", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Thông báo lỗi khi có lỗi kết nối
                        Toast.makeText(getContext(), "Error connecting to server", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void listRestaurent() {
        List<Restaurant> restaurantList = new ArrayList<>();
        String url = "http://192.168.2.33:8081/api/v1/restaurant/getHomePage";
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray restaurantArray = response.getJSONArray("data");

                            for (int i = 0; i < restaurantArray.length(); i++) {
                                JSONObject restaurantObject = restaurantArray.getJSONObject(i);
                                String restaurantName = restaurantObject.getString("restaurantTitle");
                                String imageUrl = restaurantObject.getString("image");
                                int id = restaurantObject.getInt("restaurantId");
                                restaurantList.add(new Restaurant(imageUrl, restaurantName , id));
                            }

                            // Set the data to the adapter
                            mRestaurantAdapter.setData(restaurantList);
                            rcvRestaurant.setAdapter(mRestaurantAdapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(jsonObjectRequest);
    }


    public void ActionViewFlipper() {
        ArrayList<String> mangSlider = new ArrayList<>();
        mangSlider.add("https://img.dominos.vn/banner+bogo+friday+(2).jpg");
        mangSlider.add("https://img.dominos.vn/BANNER+WEB+CHEESEBURGER.jpg");
        mangSlider.add("https://img.dominos.vn/banner+long+xuyen+(4).jpg");
        mangSlider.add("https://static.kfcvietnam.com.vn/images/content/home/carousel/lg/ComboSting.webp?v=4pGnwL");

        for (String url : mangSlider) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get()
                    .load(url)
                    .into(imageView);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
    }
}

