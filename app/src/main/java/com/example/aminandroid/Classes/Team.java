package com.example.aminandroid.Classes;

import java.util.Arrays;

public class Team {
    private String tid;
    private String name;
    private Player[] players;
    private Byte[] team_img;
    private int championships;
    private int establishment; // the year the team was established

    public Team(String tid, String name, Player[] players, Byte[] team_img, int championships,int establishment) {
        this.tid = tid;
        this.name = name;
        this.players = players;
        this.team_img = team_img;
        this.championships = championships;
        this.establishment = establishment;
    }

    public Team(String tid, String name, Player[] players, int championships,int establishment) {
        this.tid = tid;
        this.name = name;
        this.players = players;
        this.championships = championships;
        this.establishment = establishment;
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

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Byte[] getTeam_img() {
        return team_img;
    }

    public void setTeam_img(Byte[] team_img) {
        this.team_img = team_img;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    @Override
    public String toString() {
        return "Team{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", players=" + Arrays.toString(players) +
                ", team_img=" + Arrays.toString(team_img) +
                ", championships=" + championships +
                '}';
    }
}


