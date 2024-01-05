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
import android.widget.TextView;

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
    EditText playerAge,playerMvp,playerChampions,playerPoints;
    EditText teamChampions,teamYear;
    Spinner playerTeam;
    Button update,delete,goBack;
    DatabaseReference mDatabase;
    ArrayList<String> teamNames,teamId;
    TextView Hallo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        id = getIntent().getStringExtra("id");
        Hallo = findViewById(R.id.Hallo);
        Hallo.setText(id);
        info = getIntent().getStringExtra("info");
        updateImage = findViewById(R.id.update_image);
        name = findViewById(R.id.update_name);
        playerAge = findViewById(R.id.update_player_age);
        playerMvp = findViewById(R.id.update_player_mvps);
        playerChampions = findViewById(R.id.update_player_champions);
        playerPoints = findViewById(R.id.update_player_points);
        playerTeam = findViewById(R.id.update_player_team);
        teamChampions = findViewById(R.id.update_team_champions);
        teamYear = findViewById(R.id.update_team_year);
        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);
        goBack = findViewById(R.id.goback_button);
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        if(info.equals("Team")){
            teamChampions.setVisibility(View.VISIBLE);
            teamYear.setVisibility(View.VISIBLE);
        }
        else if(info.equals("Player")){
            playerAge.setVisibility(View.VISIBLE);
            playerMvp.setVisibility(View.VISIBLE);
            playerChampions.setVisibility(View.VISIBLE);
            playerPoints.setVisibility(View.VISIBLE);
            playerTeam.setVisibility(View.VISIBLE);
        }
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        goBack.setOnClickListener(this);
        fillTeamsNameAndId();
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(v.getContext(),AdminActivity.class);
        if (v.getId() == R.id.delete_button){
            if (info.equals("Team")){
                mDatabase.child("Teams").child(id).removeValue();
            }
            else {
                mDatabase.child("Players").child(id).removeValue();
            }
        }
        else if (v.getId() == R.id.update_button) {
            if (info.equals("Team")){
                mDatabase.child("Teams").child(id).setValue(new Team(id,
                        name.getText().toString(),
                        parseInt(teamChampions.getText().toString()),
                        parseInt(teamYear.getText().toString())));
            }
            else{
                mDatabase.child("Players").child(id).setValue(new Player(id,
                        name.getText().toString(),
                        Integer.parseInt(playerAge.getText().toString()),
                        Integer.parseInt(playerMvp.getText().toString()),
                        Integer.parseInt(playerChampions.getText().toString()),
                        Integer.parseInt(playerPoints.getText().toString())));
            }
        }
        startActivity(i);

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
        selectedId = teamId.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}