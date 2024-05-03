package com.example.aminandroid.Classes;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Team{

    private String tid;
    private String name;
    private int championships;
    private int establishment;// the year the team was established
    private int overAll;
    final DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    public Team(String name, int championships, int establishment) {
        this.name = name;
        this.championships = championships;
        this.establishment = establishment;
        this.overAll = 0;
    }

    public Team(String tid, String name, int championships, int establishment) {
        this.tid = tid;
        this.name = name;
        this.championships = championships;
        this.establishment = establishment;
        this.overAll = 0;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOverAll(int overAll) {
        this.overAll = overAll;
    }
    public int getChampionships() {
        return championships;
    }
    public int getOverAll() { return overAll; }
    public int getEstablishment() {
        return establishment;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    @Override
    public String toString() {
        return "Team{" +
                ", name='" + name + '\'' +
                ", championships=" + championships +
                ", establishment year=" + establishment +
                '}';
    }


    //interfaces because one method is asynchronous and cant be used without them

    public void getAllTeamPlayers(PlayersCallback callback) {
        ArrayList<Player> allPlayers = new ArrayList<>();
        final String t1Tid = this.getTid();
        //adding all of the players of a team to an arrayList for later use
        mDatabase.child("Players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (t1Tid.equals(ds.child("tid").getValue())) {

                        Player p = new Player(String.valueOf(ds.child("pid").getValue()),
                                String.valueOf(ds.child("tid").getValue()),
                                String.valueOf(ds.child("name").getValue()),
                                parseInt(String.valueOf(ds.child("pace").getValue())),
                                parseInt(String.valueOf(ds.child("shooting").getValue())),
                                parseInt(String.valueOf(ds.child("passing").getValue())),
                                parseInt(String.valueOf(ds.child("dribbling").getValue())),
                                parseInt(String.valueOf(ds.child("defense").getValue())),
                                parseInt(String.valueOf(ds.child("physical").getValue())),
                                String.valueOf(ds.child("position").getValue()));
                        allPlayers.add(p);
                    }


                }
                //calling the interface function here because all players arrayList is filled only inside this function
                callback.onCallback(allPlayers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getBestTeamPlayers(PlayersCallback callback){
        Player[] starterPlayers = new Player[5];
        this.getAllTeamPlayers(new PlayersCallback() {
            @Override
            public void onCallback(ArrayList<Player> allPlayers) {
                //0:C 1:PF 2:SF 3:SG 4:PG
                String[] positions = new String[5];
                positions[0] = "C";
                positions[1] = "PF";
                positions[2] = "SF";
                positions[3] = "SG";
                positions[4] = "PG";
                //gets Best player from each position and puts them all in an array
                for (int i = 0;i < allPlayers.size();i++){
                    for (int j = 0;j < positions.length;j++){
                        if(positions[j].equals(allPlayers.get(i).getPosition())){
                            if (starterPlayers[j]!=null) {
                                if(starterPlayers[j].getOverall() < allPlayers.get(i).getOverall())
                                    starterPlayers[j] = allPlayers.get(i);
                            }
                            else{
                                starterPlayers[j] = allPlayers.get(i);
                            }
                        }
                    }

                }
                callback.onCallback(starterPlayers);
            }

            @Override
            public void onCallback(Player[] players) {

            }

            @Override
            public void onGameStart(int[] scores) {

            }
        });


    }
    public void startGame(Team t2, PlayersCallback callback) {
        int[] scores = new int[2];
        scores[0] = 60;
        scores[1] = 60;

        Team t1 = this;

        // Perform asynchronous operation on t1
        this.getBestTeamPlayers(new PlayersCallback() {


            //increases the score of the first team based on the overall level of the players
            @Override
            public void onCallback(Player[] bestT1Team) {
                for (Player player : bestT1Team) {
                    int overAllT1 = t1.getOverAll();
                    overAllT1 += player.getOverall();
                    t1.setOverAll(overAllT1);
                }
                scores[0] += (int) (Math.random() * t1.getOverAll() / 5);

                // Proceed to the next asynchronous operation
                performSecondAsyncOperation(t2, scores, callback);
            }

            @Override
            public void onGameStart(int[] scores) {

            }
            @Override
            public void onCallback(ArrayList<Player> players) {

            }
        });
    }
    private void performSecondAsyncOperation(Team t2, int[] scores, PlayersCallback callback) {
        //increases the score of the second team based on the overall level of the players
        // Perform asynchronous operation on t2
        t2.getBestTeamPlayers(new PlayersCallback() {

            @Override
            public void onCallback(Player[] bestT2Team) {
                for (Player player : bestT2Team) {
                    int overAllT2 = t2.getOverAll();
                    overAllT2 += player.getOverall();
                    t2.setOverAll(overAllT2);
                }
                scores[1] += (int) (Math.random() * t2.getOverAll() / 5);

                // After both async operations are complete, invoke the callback with updated scores
                callback.onGameStart(scores);
            }

            @Override
            public void onGameStart(int[] scores) {

            }
            @Override
            public void onCallback(ArrayList<Player> players) {

            }
        });
    }
}




