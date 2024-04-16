package com.example.aminandroid.ClientFragments;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Admin.TeamAdapter;
import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TeamFragment extends Fragment {


    DatabaseReference mDatabase;
    List<Team> teams;
    RecyclerView recyclerView;
    TeamAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team, container, false);

        recyclerView = v.findViewById(R.id.user_team_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        teams = new ArrayList<>();

        adapter = new TeamAdapter(inflater.getContext(), teams);
        recyclerView.setAdapter(adapter);

        fillTeamInfo();

        return v;
    }

    private void fillTeamInfo() {
        //puts the information of each player in a list to put in an adapter later
        mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    teams.add(new Team(String.valueOf(ds.child("name").getValue()),
                            parseInt(String.valueOf(ds.child("championships").getValue())),
                            parseInt(String.valueOf(ds.child("establishment").getValue()))));
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}