package com.example.aminandroid;

import static java.lang.Integer.parseInt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Admin.PlayerAdapter;
import com.example.aminandroid.Classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PickPlayerOrTeamActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference mDatabase;
    List<Player> players;
    RecyclerView recyclerView;
    PlayerAdapter playerAdapter;
    Button choose,goBack;
    TextView pick1,pick2;
    String player,team,playerName,pick1_id,pick2_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_player_or_team);

        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        recyclerView = findViewById(R.id.pick_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        choose = findViewById(R.id.pick_choose_button);
        goBack = findViewById(R.id.pick_goback);
        pick1 = findViewById(R.id.pick_1);
        pick2 = findViewById(R.id.pick_2);


        String info = getIntent().getStringExtra("info");
        if(info.equals("player")){
            players = new ArrayList<>();
            playerAdapter = new PlayerAdapter(this, players);
            recyclerView.setAdapter(playerAdapter);
            choose.setOnClickListener(this);
            goBack.setOnClickListener(this);
            fillPlayerInfo();

            BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    player = intent.getStringExtra("player");
                }
            };
            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("message_subject_intent"));

        }
    }
    public void fillPlayerInfo() {
        mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    players.add(new Player(String.valueOf(ds.child("pid").getValue()),
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
                playerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == choose.getId()){
            if(player!=null) {
                mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if(player.equals(ds.getKey()))
                                playerName = String.valueOf(ds.child("name").getValue());
                        }
                        if (pick1.getText().toString().equals("Player 1")) {
                            pick1.setText(playerName);
                            pick1_id = player;
                        } else if (pick2.getText().toString().equals("Player 2") && !playerName.equals(pick1.getText().toString())) {
                            pick2.setText(playerName);
                            pick2_id = player;
                        }
                        else if(playerName.equals(pick1.getText().toString())){
                            Toast.makeText(PickPlayerOrTeamActivity.this, "Please Pick A Different Player", Toast.LENGTH_SHORT).show();
                        } else if(!pick2.getText().toString().equals("Player 2")) {
                            Toast.makeText(PickPlayerOrTeamActivity.this, "You already picked two players", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }
    }

}