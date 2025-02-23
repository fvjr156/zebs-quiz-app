package com.fvjapps.yurikafunquiz;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.fvjapps.yurikafunquiz.databinding.ActivityMainBinding;
import com.fvjapps.yurikafunquiz.ui.UIConfig;
import com.fvjapps.yurikafunquiz.ui.fragment.QuizFragment;
import com.fvjapps.yurikafunquiz.ui.fragment.QuizFileImportFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.mainAppToolbar);
        drawerLayout = binding.mainDrawerLayout;
        NavigationView navigationView = binding.mainNavigationView;

        UIConfig.setupStatusBar(this, androidx.appcompat.R.attr.colorPrimary);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, binding.mainAppToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new QuizFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_quiz);
            getSupportActionBar().setTitle("Main Menu");
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()) {
                    case R.id.nav_quiz:
                        selectedFragment = new QuizFragment();
                        getSupportActionBar().setTitle("Quiz");
                        break;
                    case R.id.nav_json:
                        selectedFragment = new QuizFileImportFragment();
                        getSupportActionBar().setTitle("Import Quiz");
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectedFragment).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}