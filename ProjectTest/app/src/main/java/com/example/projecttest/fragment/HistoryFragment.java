package com.example.projecttest.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class HistoryFragment extends Fragment {
    private RecyclerView rcvHistory;
    private HistoryAdapter historyAdapter;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        rcvHistory = rootView.findViewById(R.id.rcvHistory);
        rcvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(getContext());
        rcvHistory.setAdapter(historyAdapter);

        requestQueue = Volley.newRequestQueue(getContext());

        listHistory();
        return rootView;
    }

    public void listHistory() {
        SharedPreferences userPrefs = getActivity().getSharedPreferences("UserPreferences", getContext().MODE_PRIVATE);  // Use getActivity() for context
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
                                historyAdapter.setData(orders);
                            } else {
                                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();  // Correct context usage
                            }
                        } catch (Exception e) {
                            Log.e("HistoryFragment", "Error parsing response", e);
                            Toast.makeText(getContext(), "Error parsing response", Toast.LENGTH_SHORT).show();  // Correct context usage
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HistoryFragment", "Error fetching data", error);
                        Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();  // Correct context usage
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
