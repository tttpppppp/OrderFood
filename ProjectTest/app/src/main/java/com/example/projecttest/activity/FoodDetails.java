package com.example.projecttest.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projecttest.R;
import com.example.projecttest.model.Food;
import com.example.projecttest.model.GioHang;
import com.example.projecttest.utils.GioHangManager;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodDetails extends AppCompatActivity {

    private ImageView imageViewFood;
    private TextView textViewFoodName;
    private TextView description, txtPritextViewPrice, textIsFreeShip;
    private Button buttonaddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        imageViewFood = findViewById(R.id.imageViewFood);
        textViewFoodName = findViewById(R.id.textViewFoodName);
        buttonaddtocart = findViewById(R.id.buttonAddToCart);
        txtPritextViewPrice = findViewById(R.id.textViewPrice);
        textIsFreeShip = findViewById(R.id.textIsFreeShip);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chi tiết món ăn");
        }

        Food food = (Food) getIntent().getSerializableExtra("foodObject");

        if (food != null) {
            textViewFoodName.setText(food.getFoodName());
            double price = food.getFoodPrice(); // Giả sử price là kiểu double
            String formattedPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(price);
            txtPritextViewPrice.setText(formattedPrice);

            if (food.getIsFreeShip() == 1) {
                textIsFreeShip.setText("Miễn phí vận chuyển");
            } else {
                textIsFreeShip.setText("Không miễn phí vận chuyển");
            }

            Picasso.get().load(food.getFoodImage()).into(imageViewFood);
        } else {
            textViewFoodName.setText("No food data available");
        }
        buttonaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean found = false;
                GioHangManager gioHangManager = new GioHangManager(FoodDetails.this);
                List<GioHang> cartItems = gioHangManager.getCart();
                for (GioHang item : cartItems) {
                    if (item.getProductId() == food.getFoodId()) {
                        item.setQuantity(item.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found && food != null) {
                    GioHang gioHang = new GioHang(
                            food.getFoodId(),
                            food.getFoodName(),
                            1,
                            food.getFoodPrice(),
                            food.getFoodImage()
                    );
                    gioHangManager.addToCart(gioHang);
                    Toast.makeText(FoodDetails.this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                } else if (found) {
                    gioHangManager.saveCart(cartItems);
                    Toast.makeText(FoodDetails.this, "Sản phẩm đã có trong giỏ hàng. Số lượng đã được tăng!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FoodDetails.this, "Không thể thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
