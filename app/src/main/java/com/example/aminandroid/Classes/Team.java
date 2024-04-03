package com.example.aminandroid.Classes;

import static java.lang.Integer.parseInt;

import android.util.Log;

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
    private ArrayList<Byte> team_img;
    private int championships;
    private int establishment; // the year the team was established

    public Team(String name, ArrayList<Byte> team_img, int championships, int establishment) {
        this.name = name;
        this.team_img = team_img;
        this.championships = championships;
        this.establishment = establishment;
    }

    public Team(String name, int championships, int establishment) {
        this.name = name;
        this.championships = championships;
        this.establishment = establishment;
    }



    public Team(String tid, String name, int championships, int establishment) {

        this.tid = tid;
        this.name = name;
        this.championships = championships;
        this.establishment = establishment;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Byte> getTeam_img() {
        return team_img;
    }

    public void setTeam_img(ArrayList<Byte> team_img) {
        this.team_img = team_img;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public int getEstablishment() {
        return establishment;
    }

    public void setEstablishment(int establishment) {
        this.establishment = establishment;
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
                '}';
    }


    public ArrayList<Player> getAllTeamPlayers() {
        final String t1Tid = this.getTid();
        ArrayList<Player> allPlayers = new ArrayList<Player>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        mDatabase.child("Players").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Log.d("aaaa", t1Tid);
                    Log.d("bbbb", String.valueOf(ds.child("tid").getValue()));
                    if(t1Tid.equals(ds.child("tid").getValue())){
                        allPlayers.add(new Player(String.valueOf(ds.child("pid").getValue()),
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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return allPlayers;
    }

    public Player[] getBestTeamPlayers(){
        ArrayList<Player> allPlayers = this.getAllTeamPlayers();
        Player[] starterPlayers = new Player[5];

        //0:C 1:PF 2:SF 3:SG 4:PG
        String[] positions = new String[5];
        positions[0] = "C";
        positions[1] = "PF";
        positions[2] = "SF";
        positions[3] = "SG";
        positions[4] = "PG";
        for (int i = 0;i < allPlayers.size();i++){
            for (int j = 0;j < positions.length;j++){
                if(positions[j].equals(allPlayers.get(i).getPosition())){
                    starterPlayers[j] = allPlayers.get(i);
                }
            }

        }
        for (int i = 0;i < allPlayers.size();i++){
            for (int j = 0;j < positions.length;j++){
                if(positions[j].equals(allPlayers.get(i).getPosition())){
                    if(starterPlayers[j].getOverall() < allPlayers.get(i).getOverall())
                        starterPlayers[j] = allPlayers.get(i);
                }
            }

        }

           return starterPlayers;
    }

    public int[] startGame(Team t2) {
        int[] scores = new int[2];
        scores[0] = 40;
        scores[1] = 40;
        Player[] bestT1Team = this.getBestTeamPlayers();
        Player[]  bestT2Team = t2.getBestTeamPlayers();
        int overAllT1 = 0;
        int overAllT2 = 0;
        for (int i = 0;i<bestT1Team.length;i++){
            overAllT1+=bestT1Team[i].getOverall();
            overAllT2+=bestT2Team[i].getOverall();
        }
        overAllT1 /= 5;
        overAllT2 /= 5;
        scores[0] += (int) (overAllT1);
        scores[1] += (int) (Math.random()*overAllT2);

        return scores;
    }
}




