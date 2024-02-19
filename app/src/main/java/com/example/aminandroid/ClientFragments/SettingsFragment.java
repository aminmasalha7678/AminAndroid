package com.example.aminandroid.ClientFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.R;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    Switch darkMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        darkMode = v.findViewById(R.id.dark_mode_switch);
        darkMode.setOnClickListener(this);
        if(darkMode.getCurrentTextColor() == -1){
            darkMode.setChecked(true);
        }


        return v;
    }


    @Override
    public void onClick(View v) {
        if (darkMode.isChecked()){
            v.getContext().setTheme(R.style.Theme_AminAndroid_Light);
        }
        else{
            v.getContext().setTheme(R.style.Theme_AminAndroid_Dark);
        }
    }
}