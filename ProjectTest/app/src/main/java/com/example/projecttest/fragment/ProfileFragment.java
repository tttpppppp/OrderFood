package com.example.projecttest.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttest.R;

public class ProfileFragment extends Fragment {
    TextView txtUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtUserId = view.findViewById(R.id.txtuserid);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userID", -1);

        if (userId != -1) {
            txtUserId.setText("User ID: " + userId);
        } else {
            txtUserId.setText("No User ID found");
        }

        return view;
    }
}
