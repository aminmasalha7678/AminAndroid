package com.example.aminandroid.Admin.AdminFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPlayersFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_players, container, false);


        
        return v;
    }
    public void fillPlayerInfo(){

    }
}