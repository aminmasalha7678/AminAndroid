package com.example.aminandroid.Classes;

import java.util.Arrays;

public class Player {

    private String pid; // Player Id
    private String tid; // Team Id
    private String name;
    private int overall;
    private int pace;
    private int shooting;
    private int passing;
    private int dribbling;
    private int defense;
    private int physical;
    private String position;
    private Byte[] player_img;

    public Player(String pid, String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical,String position, Byte[] player_img) {
        this.pid = pid;
        this.tid = tid;
        this.name = name;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defense = defense;
        this.physical = physical;
        this.player_img = player_img;
        this.position = position;
        this.overall = (this.shooting+this.pace+this.passing+this.dribbling+this.defense+this.physical)/6;
    }
    public Player(String pid, String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical,String position) {
        this.pid = pid;
        this.tid = tid;
        this.name = name;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defense = defense;
        this.physical = physical;
        this.position = position;
        this.overall = (this.shooting+this.pace+this.passing+this.dribbling+this.defense+this.physical)/6;
    }
    public Player(String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical,String position) {
        this.tid = tid;
        this.name = name;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defense = defense;
        this.physical = physical;
        this.position = position;
        this.overall = (this.shooting+this.pace+this.passing+this.dribbling+this.defense+this.physical)/6;
    }


    public int getOverall(){ return (this.shooting+this.pace+this.passing+this.dribbling+this.defense+this.physical)/6; }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Byte[] getPlayer_img() {
        return player_img;
    }

    public void setPlayer_img(Byte[] player_img) {
        this.player_img = player_img;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pid='" + pid + '\'' +
                ", tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", pace=" + pace +
                ", shooting=" + shooting +
                ", passing=" + passing +
                ", dribbling=" + dribbling +
                ", defense=" + defense +
                ", Physical=" + physical +
                ", player_img=" + Arrays.toString(player_img) +
                '}';
    }


    public int[] startGame(Player p2){
        int[] scores = new int[2];
        int pick1_score = 0;
        int pick2_score = 0;
        int pick1_random,pick2_random;
        while(pick2_score != 7 && pick1_score != 7){
            pick1_random = (int) (Math.random()*this.getOverall());
            pick2_random = (int) (Math.random()*p2.getOverall());
            if(pick1_random > pick2_random){
                pick1_score++;
            }
            else if(pick2_random > pick1_random) {
                pick2_score++;
            }

        }
        scores[0] = pick1_score;
        scores[1] = pick2_score;

        return scores;
    }
}
