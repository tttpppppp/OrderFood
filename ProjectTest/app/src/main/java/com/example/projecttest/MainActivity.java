package com.example.projecttest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Animation topAnima , bottomAnima;
    ImageView image;
    TextView logo , sologan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAnima = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        bottomAnima = AnimationUtils.loadAnimation(this ,R.anim.bottom_animation);

        image = findViewById(R.id.imageshop);
        logo = findViewById(R.id.logo);
        sologan = findViewById(R.id.cartDescription);

        image.setAnimation(topAnima);
        logo.setAnimation(bottomAnima);
        sologan.setAnimation(bottomAnima);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
