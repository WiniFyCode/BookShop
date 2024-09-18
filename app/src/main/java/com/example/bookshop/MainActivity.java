package com.example.bookshop;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookshop.Fragment.CartFragment;
import com.example.bookshop.Fragment.FavoriteFragment;
import com.example.bookshop.Fragment.HomeFragment;
import com.example.bookshop.Fragment.NotificationFragment;
import com.example.bookshop.Fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // khai bao
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    SearchView searchView;
    AppCompatButton buttonCart;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    FloatingActionButton floatingActionButton;

    // khai bao cac fragment
    FragmentTransaction fragmentTransaction;
    CartFragment cartFragment;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;
    NotificationFragment notificationFragment;
    SettingFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        anhxa();
        bottomNavigationView.setBackground(null);// bỏ bóng mờ của transparent
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        getSupportActionBar().setTitle("Hi");
//        searchView.setIconifiedByDefault(false); // tuong duong voi: app:iconifiedByDefault="false" | ben xml
        actionBarDrawerToggle.syncState();

        // xu ly su kien
        XuLyNavigationView();
        XuLyBottomNavgationView();

        // khoi tao fragment
        cartFragment = new CartFragment();
        homeFragment = new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        notificationFragment = new NotificationFragment();
        settingsFragment = new SettingFragment();

        //set default fragment
        LoadFragment(homeFragment);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadFragment(homeFragment);
            }
        });

    }

    private void XuLyBottomNavgationView() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mnuCart:
                        LoadFragment(cartFragment);
                        break;
                    case R.id.mnuFavourites:
                        LoadFragment(favoriteFragment);
                        break;
                    case R.id.mnuNotifications:
                        LoadFragment(notificationFragment);
                        break;
                    case R.id.mnuSettings:
                        LoadFragment(settingsFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void XuLyNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mnuHome:
                        break;
                    case R.id.mnuPortfolio:
                        break;
                    case R.id.mnuAccount:
                        break;
                    case R.id.mnuSetting:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void anhxa() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchView);
        buttonCart = findViewById(R.id.btnCart);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navigationView = findViewById(R.id.navigationView);
        floatingActionButton = findViewById(R.id.fabHome);
    }

    private void LoadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}