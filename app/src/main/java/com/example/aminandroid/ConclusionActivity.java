package com.example.aminandroid;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.Classes.Team;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConclusionActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView pick1_image, pick2_image;
    DatabaseReference mDatabase;
    TextView pick1_name, pick2_name, pick1_score, pick2_score, gameHighlights;
    OkHttpClient client;
    Button goBack;
    String pick1_id, pick2_id, info, winner;
    Player p1, p2;
    Team t1, t2;
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

        goBack.setOnClickListener(this);

        pick1_id = getIntent().getStringExtra("pick1");
        pick2_id = getIntent().getStringExtra("pick2");
        info = getIntent().getStringExtra("info");

        gameResults();
    }
    private void gameResults() {
        client = new OkHttpClient();

        if (info.equals("player")) {
            mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
                //gives the proper values for p1 (Player 1) and p2 (Player 2)
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (pick1_id.equals(ds.getKey())) {
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
                        } else if (pick2_id.equals(ds.getKey())) {
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
                    if(scores[0]>scores[1]){
                        winner = p1.getName();
                    }
                    else{
                        winner = p2.getName();
                    }

                    sendRequest("write highlights no longer than 60 words for a fictional 1 vs 1 in basketball (until 7 points) between a player named "+ p1.getName() +"and another player named "+ p2.getName()+"which "+ winner +"won and the score was" + scores[0] +"to" +scores[1] +". "+p1.getName()+"stats are"+p1.toString()+"and "+p2.getName()+"stats are" +p2.toString() +"stats are ranked from 0-99 (0 being the worst 99 being the best)");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        else if (info.equals("team")) {
            mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
                //gives the proper values for t1 (Team 1) and t2 (Team 2)
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (pick1_id.equals(ds.getKey())) {
                            t1 = new Team(String.valueOf(ds.child("tid").getValue()),
                                    String.valueOf(ds.child("name").getValue()),
                                    parseInt(String.valueOf(ds.child("championships").getValue())),
                                    parseInt(String.valueOf(ds.child("establishment").getValue())));
                            pick1_name.setText(t1.getName());
                        }
                        else if (pick2_id.equals(ds.getKey())) {
                            t2 = new Team(String.valueOf(ds.child("tid").getValue()),
                                    String.valueOf(ds.child("name").getValue()),
                                    parseInt(String.valueOf(ds.child("championships").getValue())),
                                    parseInt(String.valueOf(ds.child("establishment").getValue())));
                            pick2_name.setText(t2.getName());
                        }
                    }

                    t1.startGame(t2, new Team.GameStartCallback() {
                        @Override
                        public void onGameStart(int[] scores) {
                            pick1_score.setText(String.valueOf(scores[0]));
                            pick2_score.setText(String.valueOf(scores[1]));
                            if(scores[0]>scores[1]){
                                winner = t1.getName();
                            }
                            else{
                                winner = t2.getName();
                            }
                            sendRequest("write highlights no longer than 60 words for a fictional team vs team in basketball between" + t1.getName() + "and"+ t2.getName()+"this game is hypothetical do not include player names in the highlights "+"the team who won is"+ winner+"with a score of"+scores[0]+"to"+scores[1]
                            );
                        }
                    });




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // Method to send HTTP request to GPT-3.5-turbo API
    private void sendRequest(String message) {
        JSONObject json = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject messageObj = new JSONObject();
        try {
            messageObj.put("role", "user");
            messageObj.put("content", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        messagesArray.put(messageObj);
        try {
            json.put("model", "gpt-3.5-turbo");
            json.put("max_tokens", 200);
            json.put("messages", messagesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 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")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // Handle failure if needed
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseBody);

                        // Check if the response contains 'choices' array
                        if (jsonResponse.has("choices")) {
                            JSONArray choicesArray = jsonResponse.getJSONArray("choices");

                            // Check if the 'choices' array is not empty
                            if (choicesArray.length() > 0) {
                                JSONObject choice = choicesArray.getJSONObject(0);

                                // Extract the message content
                                JSONObject message = choice.getJSONObject("message");
                                String assistantMessage = message.getString("content");
                                gameHighlights.setText(assistantMessage);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle unsuccessful response
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.conc_goback){
            startActivity(new Intent(ConclusionActivity.this, MainActivity.class));
        }
    }
}



