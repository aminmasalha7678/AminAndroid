package com.example.aminandroid.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddPlayerFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    EditText playerName, playerAge, playerMvps, playerChampions, playerPoints;
    Button addPlayer;
    Spinner playerTeam;
    DatabaseReference mDatabase;
    ArrayList<String> teamNames,teamId;
    String selectedId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_player, container, false);

        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        playerName = (EditText) v.findViewById(R.id.addplayer_name);
        playerAge = (EditText) v.findViewById(R.id.addplayer_age);
        playerChampions = (EditText) v.findViewById(R.id.addplayer_champions);
        playerPoints = (EditText) v.findViewById(R.id.addplayer_points);
        playerMvps = (EditText) v.findViewById(R.id.addplayer_mvps);
        playerTeam = (Spinner) v.findViewById(R.id.addplayer_team);
        addPlayer = (Button) v.findViewById(R.id.addplayer_button);


        addPlayer.setOnClickListener(this);
        fillTeamsNameAndId();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, teamNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerTeam.setAdapter(adapter);
        playerTeam.setOnItemSelectedListener(this);

        return v;
    }
    private void fillTeamsNameAndId(){

        teamNames = new ArrayList<String>();
        teamId = new ArrayList<String>();
        mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String name = String.valueOf(ds.child("name").getValue());
                    String id = String.valueOf(ds.child("tid").getValue());
                    teamNames.add(name);
                    teamId.add(id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addplayer_button) {
            Player player = new Player(selectedId,playerName.getText().toString(),Integer.parseInt(playerAge.getText().toString()),Integer.parseInt(playerMvps.getText().toString()),Integer.parseInt(playerChampions.getText().toString()),Integer.parseInt(playerPoints.getText().toString()));
            DatabaseReference pushPlayer = mDatabase.child("Players").push();
            Log.d("hello",selectedId+"iuh");
            player.setPid(pushPlayer.getKey());
            pushPlayer.setValue(player);
            Toast.makeText(getContext(),"Player Added Succesfully",Toast.LENGTH_SHORT).show();
            playerName.setText("");
            playerAge.setText("");
            playerMvps.setText("");
            playerChampions.setText("");
            playerPoints.setText("");

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("item", "dddddd");
        ((TextView) adapterView.getChildAt(i)).setTextColor(Color.BLACK);
        selectedId = teamId.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}