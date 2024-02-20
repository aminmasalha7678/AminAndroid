package com.example.aminandroid;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aminandroid.Classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConclusionActivity extends AppCompatActivity {

    ImageView pick1_image,pick2_image;
    DatabaseReference mDatabase;
    TextView pick1_name,pick2_name,pick1_score,pick2_score,gameHighlights;
    Button goBack;
    String pick1_id,pick2_id,info;
    Player p1,p2;
    int[] scores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);

        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        pick1_image = findViewById(R.id.conc_pick1_image);
        pick2_image = findViewById(R.id.conc_pick2_image);
        pick1_name = findViewById(R.id.conc_pick1_name);
        pick2_name = findViewById(R.id.conc_pick2_name);
        pick1_score = findViewById(R.id.conc_pick1_score);
        pick2_score = findViewById(R.id.conc_pick2_score);
        gameHighlights = findViewById(R.id.conc_highlights);
        goBack = findViewById(R.id.conc_goback);

        pick1_id = getIntent().getStringExtra("pick1");
        pick2_id = getIntent().getStringExtra("pick2");
        info = getIntent().getStringExtra("info");

        gameResults();
    }
    private void gameResults(){
        if(info.equals("player")){
            mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if(pick1_id.equals(ds.getKey())){
                            p1 = new Player(String.valueOf(ds.child("pid").getValue()),
                                String.valueOf(ds.child("tid").getValue()),
                                String.valueOf(ds.child("name").getValue()),
                                parseInt(String.valueOf(ds.child("pace").getValue())),
                                parseInt(String.valueOf(ds.child("shooting").getValue())),
                                parseInt(String.valueOf(ds.child("passing").getValue())),
                                parseInt(String.valueOf(ds.child("dribbling").getValue())),
                                parseInt(String.valueOf(ds.child("defense").getValue())),
                                parseInt(String.valueOf(ds.child("physical").getValue())),
                                String.valueOf(ds.child("position").getValue()));
                            pick1_name.setText(p1.getName());
                        }
                        else if(pick2_id.equals(ds.getKey())){
                            p2 = new Player(String.valueOf(ds.child("pid").getValue()),
                                    String.valueOf(ds.child("tid").getValue()),
                                    String.valueOf(ds.child("name").getValue()),
                                    parseInt(String.valueOf(ds.child("pace").getValue())),
                                    parseInt(String.valueOf(ds.child("shooting").getValue())),
                                    parseInt(String.valueOf(ds.child("passing").getValue())),
                                    parseInt(String.valueOf(ds.child("dribbling").getValue())),
                                    parseInt(String.valueOf(ds.child("defense").getValue())),
                                    parseInt(String.valueOf(ds.child("physical").getValue())),
                                    String.valueOf(ds.child("position").getValue()));
                            pick2_name.setText(p2.getName());
                        }
                    }
                    scores = p1.startGame(p2);
                    pick1_score.setText(String.valueOf(scores[0]));
                    pick2_score.setText(String.valueOf(scores[1]));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}