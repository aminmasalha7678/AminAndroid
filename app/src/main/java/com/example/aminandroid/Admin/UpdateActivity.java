package com.example.aminandroid.Admin;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String id,info,selectedId;
    ImageButton updateImage;
    EditText name;
    EditText playerPace,playerShooting,playerPassing,playerDribbling,playerDefense,playerPhysical;
    EditText teamChampions,teamYear;
    Spinner playerTeam,playerPosition;
    Button update,delete,goBack;
    DatabaseReference mDatabase;
    ArrayList<String> teamNames,teamId,positions;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //define variables used in the activity
        id = getIntent().getStringExtra("id");
        info = getIntent().getStringExtra("info");
        playerPace = findViewById(R.id.update_player_pac);
        playerShooting = findViewById(R.id.update_player_sho);
        playerPassing = findViewById(R.id.update_player_pas);
        playerDribbling = findViewById(R.id.update_player_dri);
        playerDefense = findViewById(R.id.update_player_def);
        playerPhysical = findViewById(R.id.update_player_phy);
        updateImage = findViewById(R.id.update_image);
        name = findViewById(R.id.update_name);
        playerTeam = findViewById(R.id.update_player_team);
        playerPosition = findViewById(R.id.update_player_position);
        teamChampions = findViewById(R.id.update_team_champions);
        teamYear = findViewById(R.id.update_team_year);
        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);
        goBack = findViewById(R.id.goback_button);
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        //sets up positions info to use in the adapter
        positions = new ArrayList<String>();

        positions.add("C");
        positions.add("PF");
        positions.add("SF");
        positions.add("SG");
        positions.add("PG");

        //sets up the positions adapter for the spinner view playerPosition
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, positions);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerPosition.setAdapter(positionAdapter);

        playerPosition.setOnItemSelectedListener(this);

        //checks if the object being updated is a team or a player and sets the visibility of views based on which one
        if(info.equals("Team")){
            mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if(ds.child("tid").getValue().toString().equals(id)){
                            teamChampions.setText(ds.child("championships").getValue().toString());
                            teamYear.setText(ds.child("establishment").getValue().toString());
                            name.setText(ds.child("name").getValue().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            teamChampions.setVisibility(View.VISIBLE);
            teamYear.setVisibility(View.VISIBLE);
        }
        else if(info.equals("Player")){
            playerPosition.setVisibility(View.VISIBLE);
            playerTeam.setVisibility(View.VISIBLE);
            playerPace.setVisibility(View.VISIBLE);
            playerShooting.setVisibility(View.VISIBLE);
            playerDribbling.setVisibility(View.VISIBLE);
            playerDefense.setVisibility(View.VISIBLE);
            playerPhysical.setVisibility(View.VISIBLE);
            playerPassing.setVisibility(View.VISIBLE);
            fillTeamsNameAndId();
            mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if(ds.child("pid").getValue().toString().equals(id)){
                            name.setText(ds.child("name").getValue().toString());
                            playerPace.setText(ds.child("pace").getValue().toString());
                            playerShooting.setText(ds.child("shooting").getValue().toString());
                            playerDribbling.setText(ds.child("dribbling").getValue().toString());
                            playerDefense.setText(ds.child("defense").getValue().toString());
                            playerPhysical.setText(ds.child("physical").getValue().toString());
                            playerPassing.setText(ds.child("passing").getValue().toString());
                            ArrayAdapter<String> adapter = (ArrayAdapter<String>) playerPosition.getAdapter();
                            if (adapter != null) {
                                for (int i = 0; i < adapter.getCount(); i++) {
                                    if (adapter.getItem(i).equals(ds.child("position").getValue().toString())) {
                                        // Found the position matching the desired text
                                        playerPosition.setSelection(i); // Set the Spinner to display the desired text
                                    }
                                }
                            }
                            for (int i = 0; i < teamId.size(); i++) {
                                if (teamId.get(i).equals(ds.child("tid").getValue().toString())) {
                                    // Found the position matching the desired text
                                    playerTeam.setSelection(i); // Set the Spinner to display the desired text
                                }
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        goBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(v.getContext(),AdminActivity.class);
        if (v.getId() == R.id.delete_button){
            //deletes the object from firebase
            if (info.equals("Team")){
                mDatabase.child("Teams").child(id).removeValue();
            }
            else {
                mDatabase.child("Players").child(id).removeValue();
            }
        }
        else if (v.getId() == R.id.update_button) {
            //updates the objects values in firebase
           if (info.equals("Team")){
                mDatabase.child("Teams").child(id).setValue(new Team(id,
                        name.getText().toString(),
                        parseInt(teamChampions.getText().toString()),
                        parseInt(teamYear.getText().toString())));
            }
            else{
                mDatabase.child("Players").child(id).setValue(new Player(id,
                        selectedId,
                        name.getText().toString(),
                        parseInt(playerPace.getText().toString()),
                        parseInt(playerShooting.getText().toString()),
                        parseInt(playerPassing.getText().toString()),
                        parseInt(playerDribbling.getText().toString()),
                        parseInt(playerDefense.getText().toString()),
                        parseInt(playerPhysical.getText().toString()),
                        playerPosition.getSelectedItem().toString()));
            }
        }
        startActivity(i);

    }
    private void fillTeamsNameAndId(){
        //fills the teams names for the playerTeam spinner
        teamNames = new ArrayList<String>();
        teamId = new ArrayList<String>();
        mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //adds each team name and id to the name and id arraylists
                    String name = String.valueOf(ds.child("name").getValue());
                    String id = String.valueOf(ds.child("tid").getValue());
                    teamNames.add(name);
                    teamId.add(id);
                }
                //sets up the playerTeam spinner by setting up and filling the information in an adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateActivity.this, android.R.layout.simple_spinner_dropdown_item, teamNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                playerTeam.setAdapter(adapter);

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();

                // Set the listener after the adapter is set
                playerTeam.setOnItemSelectedListener(UpdateActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        if(view != null) {
            //checks which spinner is being used the positions or the player_team
            if (parent.getId() == R.id.update_player_team)
                selectedId = teamId.get(i);
            else
                position = positions.get(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}