package com.example.aminandroid.Admin.AdminFragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
    EditText playerName, playerAge, playerMvps, playerChampions, playerPoints;
    Button addPlayer;
    ImageButton playerImage;
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

        playerImage = (ImageButton) v.findViewById(R.id.addplayer_image);
        playerName = (EditText) v.findViewById(R.id.addplayer_name);
        playerAge = (EditText) v.findViewById(R.id.addplayer_age);
        playerChampions = (EditText) v.findViewById(R.id.addplayer_champions);
        playerPoints = (EditText) v.findViewById(R.id.addplayer_points);
        playerMvps = (EditText) v.findViewById(R.id.addplayer_mvps);
        playerTeam = (Spinner) v.findViewById(R.id.addplayer_team);
        addPlayer = (Button) v.findViewById(R.id.addplayer_button);

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
        if (v.getId() == R.id.addplayer_button) {
            Player player = new Player(selectedId,playerName.getText().toString(),Integer.parseInt(playerAge.getText().toString()),Integer.parseInt(playerMvps.getText().toString()),Integer.parseInt(playerChampions.getText().toString()),Integer.parseInt(playerPoints.getText().toString()));
            DatabaseReference pushPlayer = mDatabase.child("Players").push();
            player.setPid(pushPlayer.getKey());
            pushPlayer.setValue(player);
            Toast.makeText(getContext(),"Player Added Succesfully",Toast.LENGTH_SHORT).show();
            playerName.setText("");
            playerAge.setText("");
            playerMvps.setText("");
            playerChampions.setText("");
            playerPoints.setText("");

        }
        if(v.getId() == R.id.addplayer_image){
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedId = teamId.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}