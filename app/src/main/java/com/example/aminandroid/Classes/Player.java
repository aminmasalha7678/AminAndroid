package com.example.aminandroid.Classes;

import java.util.Arrays;

public class Player {

    private String pid; // Player Id
    private String tid; // Team Id
    private String name;
    private int pace;
    private int shooting;
    private int passing;
    private int dribbling;
    private int defense;
    private int physical;
    private Byte[] player_img;

    public Player(String pid, String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical, Byte[] player_img) {
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
    }
    public Player(String pid, String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical) {
        this.pid = pid;
        this.tid = tid;
        this.name = name;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defense = defense;
        this.physical = physical;
    }
    public Player(String tid, String name, int pace, int shooting, int passing, int dribbling, int defense, int physical) {
        this.tid = tid;
        this.name = name;
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defense = defense;
        this.physical = physical;
    }


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
}
