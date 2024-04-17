package com.example.aminandroid.Admin.AdminFragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddPlayerFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    private static int RESULT_LOAD_IMAGE = 1;
    EditText playerName, playerPace, playerShooting, playerPassing, playerDribbling, playerDefense, playerPhysical;
    Button addPlayer;
    ImageButton playerImage;
    Spinner playerTeam,playerPosition;
    DatabaseReference mDatabase;
    ArrayList<String> teamNames,teamId,positions;
    String selectedId;
    String position;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_add_player, container, false);

        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        playerImage = v.findViewById(R.id.add_admin_player_image);
        playerName = v.findViewById(R.id.add_admin_player_name);
        playerPace = v.findViewById(R.id.add_admin_player_pace);
        playerShooting = v.findViewById(R.id.add_admin_player_shooting);
        playerPassing = v.findViewById(R.id.add_admin_player_passing);
        playerDribbling = v.findViewById(R.id.add_admin_player_drib);
        playerDefense = v.findViewById(R.id.add_admin_player_def);
        playerPhysical = v.findViewById(R.id.add_admin_player_phy);
        playerTeam = v.findViewById(R.id.add_admin_player_team);
        playerPosition = v.findViewById(R.id.add_admin_player_position);
        addPlayer = v.findViewById(R.id.add_admin_player_button);

        positions = new ArrayList<String>();

        positions.add("C");
        positions.add("PF");
        positions.add("SF");
        positions.add("SG");
        positions.add("PG");

        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, positions);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerPosition.setAdapter(positionAdapter);

        playerPosition.setOnItemSelectedListener(this);

        playerImage.setOnClickListener(this);
        addPlayer.setOnClickListener(this);
        fillTeamsNameAndId();

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

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, teamNames);
                if(adapter != null)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                playerTeam.setAdapter(adapter);

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();

                // Set the listener after the adapter is set
                playerTeam.setOnItemSelectedListener(AddPlayerFragment.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_admin_player_button) {
            Player player = new Player("aa",selectedId,
                    playerName.getText().toString(),
                    Integer.parseInt(playerPace.getText().toString()),
                    Integer.parseInt(playerShooting.getText().toString()),
                    Integer.parseInt(playerPassing.getText().toString()),
                    Integer.parseInt(playerDribbling.getText().toString()),
                    Integer.parseInt(playerDefense.getText().toString()),
                    Integer.parseInt(playerPhysical.getText().toString()),
                    position);

            DatabaseReference pushPlayer = mDatabase.child("Players").push();
            player.setPid(pushPlayer.getKey());
            pushPlayer.setValue(player);
            Toast.makeText(getContext(),"Player Added Succesfully",Toast.LENGTH_SHORT).show();
            playerName.setText("");
            playerPace.setText("");
            playerShooting.setText("");
            playerPassing.setText("");
            playerDribbling.setText("");
            playerDefense.setText("");
            playerPhysical.setText("");

        }
        if(v.getId() == R.id.add_admin_player_image){
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(view != null) {
            if (adapterView.getId() == R.id.add_admin_player_team)
                selectedId = teamId.get(i);
            else
                position = positions.get(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}