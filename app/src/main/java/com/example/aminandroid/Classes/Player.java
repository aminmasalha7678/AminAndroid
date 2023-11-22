package com.example.aminandroid.Classes;

import java.util.Arrays;

public class Player {

    private String pid; // Player Id
    private String tid; // Team Id
    private String name;
    private int age;
    private int mvps;
    private int points;
    private int championships;

    private Byte[] player_img;

    public Player(String tid,String pid,String name, int age, int mvps, int championships, int points, Byte[] player_img) {
        this.tid = tid;
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.mvps = mvps;
        this.championships = championships;
        this.points = points;
        this.player_img = player_img;
    }
    public Player(String tid,String pid,String name, int age, int mvps, int championships, int points) {
        this.tid = tid;
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.mvps = mvps;
        this.championships = championships;
        this.points = points;
    }
    public Player(String tid,String name, int age, int mvps, int championships, int points) {
        this.tid = tid;
        this.name = name;
        this.age = age;
        this.mvps = mvps;
        this.championships = championships;
        this.points = points;
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
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getMvps() {
        return mvps;
    }

    public void setMvps(int mvps) {
        this.mvps = mvps;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
                "name='" + name + '\'' +
                ", age=" + age +
                ", championships=" + championships +
                ", mvps=" + mvps +
                ", points=" + points +
                ", player_img=" + Arrays.toString(player_img) +
                '}';
    }
}
