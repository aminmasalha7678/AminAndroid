package com.example.aminandroid.ClientFragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.aminandroid.R;

import static androidx.core.app.ActivityCompat.recreate;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    Switch darkMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        darkMode = v.findViewById(R.id.dark_mode_switch);
        darkMode.setOnClickListener(this);
        if(darkMode.getCurrentTextColor() == -1646107){
            darkMode.setChecked(true);
        }


        return v;
    }


    @Override
    public void onClick(View v) {
        if (darkMode.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        recreate((Activity) v.getContext());
    }
}