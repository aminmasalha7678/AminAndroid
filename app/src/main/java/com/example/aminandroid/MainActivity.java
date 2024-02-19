package com.example.aminandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.aminandroid.Admin.AdminActivity;
import com.example.aminandroid.ClientFragments.GameFragment;
import com.example.aminandroid.ClientFragments.HomeFragment;
import com.example.aminandroid.ClientFragments.PlayerFragment;
import com.example.aminandroid.ClientFragments.SettingsFragment;
import com.example.aminandroid.ClientFragments.TeamFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;
    TextView name,email;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        if(user != null) {
            View headerView = navigationView.getHeaderView(0);
            if(user.getDisplayName().startsWith("admin: ")){
                MenuItem admin = navigationView.getMenu().findItem(R.id.nav_admin);
                admin.setVisible(true);
            }
            email = headerView.findViewById(R.id.email_header);
            name = headerView.findViewById(R.id.name_header);
            email.setText(user.getEmail());
            name.setText(user.getDisplayName());
        }
        else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        else if (item.getItemId() == R.id.nav_teams) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TeamFragment()).commit();
        }
        else if (item.getItemId() == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        }
        else if (item.getItemId() == R.id.nav_players) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlayerFragment()).commit();
        }
        else if (item.getItemId() == R.id.nav_games) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GameFragment()).commit();
        }
        else if (item.getItemId() == R.id.nav_admin) {
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        }
        else if (item.getItemId() == R.id.nav_logout) {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
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