package com.example.aminandroid.Admin.AdminFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.R;


public class AdminHomeFragment extends Fragment implements View.OnClickListener {

Button viewPlayers,viewTeams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_home, container, false);

        viewPlayers = (Button) v.findViewById(R.id.adminHome_viewPlayers);
        viewTeams = (Button) v.findViewById(R.id.adminHome_viewTeams);

        viewPlayers.setOnClickListener(this);
        viewTeams.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.adminHome_viewPlayers){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new ViewPlayersFragment()).commit();
        }
        else if (view.getId() == R.id.adminHome_viewTeams){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, new ViewTeamsFragment()).commit();
        }
    }
}