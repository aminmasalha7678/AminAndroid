package com.example.aminandroid.Classes;

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
    public void setPid(String pid) { this.pid = pid; }
    public String getTid() {
        return tid;
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
    public int getShooting() {
        return shooting;
    }
    public int getPassing() {
        return passing;
    }
    public int getDribbling() {
        return dribbling;
    }
    public int getDefense() {
        return defense;
    }
    public int getPhysical() { return physical; }
    public String getPosition() { return position; }
    @Override
    public String toString() {
        return "Player{" +
                ", pace=" + pace +
                ", shooting=" + shooting +
                ", passing=" + passing +
                ", dribbling=" + dribbling +
                ", defense=" + defense +
                ", Physical=" + physical +
                '}';
    }
    public int[] startGame(Player p2){
        //begins game between players based on luck but gives better chance to the player with better overAll stats
        int[] scores = new int[2];
        int pick1_score = 0;
        int pick2_score = 0;
        int pick1_random,pick2_random;
        //checks if the game ended by any of the players reaching 7 if not continues to increase players scores based on luck (but gives better chance to the player with better overAll stats)
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
