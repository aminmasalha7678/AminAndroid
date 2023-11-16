package com.example.aminandroid.Classes;

import java.util.Arrays;

public class Team {

    private String tid;
    private String name;
    private Byte[] team_img;
    private int championships;
    private int establishment; // the year the team was established

    public Team(String name, Byte[] team_img, int championships, int establishment) {
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
                ", team_img=" + Arrays.toString(team_img) +
                ", championships=" + championships +
                '}';
    }
}




