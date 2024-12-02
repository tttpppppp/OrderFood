package com.example.projecttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttest.R;

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
