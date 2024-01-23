package com.example.aminandroid.Admin.AdminFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.Admin.AdminActivity;
import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AddTeamFragment extends Fragment implements View.OnClickListener {

    EditText teamName,teamYear,teamChampion;
    Button addTeam;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_add_team, container, false);

        teamName = v.findViewById(R.id.addteam_name);
        teamYear = v.findViewById(R.id.addteam_year);
        teamChampion = v.findViewById(R.id.addteam_championships);
        addTeam = v.findViewById(R.id.addteam_button);
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        addTeam.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addteam_button){
            Team team = new Team(teamName.getText().toString(),Integer.parseInt(teamChampion.getText().toString()),Integer.parseInt(teamYear.getText().toString()));
            DatabaseReference pushTeam = mDatabase.child("Teams").push();
            team.setTid(pushTeam.getKey());
            pushTeam.setValue(team);
            Toast.makeText(getContext(),"Team Added Succesfully",Toast.LENGTH_SHORT).show();
            teamName.setText("");
            teamChampion.setText("");
            teamYear.setText("");
        }
    }

}