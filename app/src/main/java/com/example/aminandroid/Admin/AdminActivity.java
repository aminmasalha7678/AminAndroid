package com.example.aminandroid.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.aminandroid.Fragments.AddGameFragment;
import com.example.aminandroid.Fragments.AddPlayerFragment;
import com.example.aminandroid.Fragments.AddTeamFragment;
import com.example.aminandroid.Fragments.HomeFragment;
import com.example.aminandroid.LoginActivity;
import com.example.aminandroid.MainActivity;
import com.example.aminandroid.R;
import com.example.aminandroid.SignupActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        NavigationView navigationView = findViewById(R.id.admin_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(user == null || !user.getDisplayName().startsWith("admin: ")){
            startActivity(new Intent(AdminActivity.this, SignupActivity.class));
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.admin_nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.admin_nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new HomeFragment()).commit();
        }
        else if (item.getItemId() == R.id.admin_nav_team) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new AddTeamFragment()).commit();
        }
        else if (item.getItemId() == R.id.admin_nav_player) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new AddPlayerFragment()).commit();
        }
        else if (item.getItemId() == R.id.admin_nav_game) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new AddGameFragment()).commit();
        }
        else if (item.getItemId() == R.id.admin_nav_go_back) {
            startActivity(new Intent(AdminActivity.this, MainActivity.class));
        }
        else if (item.getItemId() == R.id.admin_nav_logout) {
            mAuth.signOut();
            startActivity(new Intent(AdminActivity.this, LoginActivity.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

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