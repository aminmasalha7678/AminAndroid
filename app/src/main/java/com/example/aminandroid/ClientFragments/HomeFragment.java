package com.example.aminandroid.ClientFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.R;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button player_button,team_button,game_button;
    NavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        navigationView = getActivity().findViewById(R.id.nav_view);

        player_button = v.findViewById(R.id.home_players_button);
        team_button = v.findViewById(R.id.home_teams_button);
        game_button = v.findViewById(R.id.home_game_button);

        game_button.setOnClickListener(this);
        team_button.setOnClickListener(this);
        player_button.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.home_players_button){
            navigationView.getMenu().findItem(R.id.nav_players).setChecked(true);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlayerFragment()).commit();
        }
        else if(v.getId() == R.id.home_teams_button) {
            navigationView.getMenu().findItem(R.id.nav_teams).setChecked(true);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TeamFragment()).commit();
        }
        else{
            navigationView.getMenu().findItem(R.id.nav_games).setChecked(true);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GameFragment()).commit();
        }
    }
}