package com.example.aminandroid.Admin.AdminFragments;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Admin.PlayerAdapter;
import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewPlayersFragment extends Fragment {

    DatabaseReference mDatabase;
    List<Player> Players;
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_players, container, false);
        recyclerView = v.findViewById(R.id.players_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        Players = new ArrayList<>();
        adapter = new PlayerAdapter(inflater.getContext(), Players);
        recyclerView.setAdapter(adapter);

        fillPlayerInfo();

        return v;
    }

    public void fillPlayerInfo() {
        mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Players.add(new Player(String.valueOf(ds.child("pid").getValue()),
                            String.valueOf(ds.child("tid").getValue()),
                            String.valueOf(ds.child("name").getValue()),
                            parseInt(String.valueOf(ds.child("pace").getValue())),
                            parseInt(String.valueOf(ds.child("shooting").getValue())),
                            parseInt(String.valueOf(ds.child("passing").getValue())),
                            parseInt(String.valueOf(ds.child("dribbling").getValue())),
                            parseInt(String.valueOf(ds.child("defense").getValue())),
                            parseInt(String.valueOf(ds.child("physical").getValue())),
                            String.valueOf(ds.child("position").getValue())));
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}