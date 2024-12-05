package com.example.projecttest.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecttest.R;
import com.example.projecttest.adapter.HistoryAdapter;
import com.example.projecttest.model.HistoryOrder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class HistoryOrderActivity extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private HistoryAdapter historyAdapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        rcvHistory = findViewById(R.id.rcvHistory);
        rcvHistory.setLayoutManager(new LinearLayoutManager(this));

        historyAdapter = new HistoryAdapter(this);
        rcvHistory.setAdapter(historyAdapter);

        // Initialize the request queue
        requestQueue = Volley.newRequestQueue(this);

        listHistory();
    }

    public void listHistory() {
        SharedPreferences userPrefs = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        int userId = userPrefs.getInt("userID", -1);
        String url = "http://192.168.2.33:8081/api/v1/order/getAllOrder?userId=" + userId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("status") == 200) {
                                String dataJson = response.getJSONArray("data").toString();
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<HistoryOrder>>() {}.getType();
                                List<HistoryOrder> orders = gson.fromJson(dataJson, listType);

                                // Update adapter with new data
                                historyAdapter.setData(orders);
                            } else {
                                Toast.makeText(HistoryOrderActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("HistoryOrderActivity", "Error parsing response", e);
                            Toast.makeText(HistoryOrderActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HistoryOrderActivity", "Error fetching data", error);
                        Toast.makeText(HistoryOrderActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}
