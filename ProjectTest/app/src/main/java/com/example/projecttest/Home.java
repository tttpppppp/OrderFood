package com.example.projecttest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {
                Toast.makeText(Home.this, "Serach is expand", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {
                Toast.makeText(Home.this, "Serach is expand", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnActionExpandListener(onActionExpandListener);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search...");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.cart) {
            Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.noti) {
            Toast.makeText(this, "noti", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
