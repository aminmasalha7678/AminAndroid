package com.example.aminandroid.Classes;

public class Game {

    private String gid;
    private String hid; // Home Team Id
    private String aid; // Away Team Id
    private String stadium;
    private int ticket_price;
    private int ticket_stock;

    public Game(String gid, String hid, String aid, String stadium, int ticket_price, int ticket_stock) {
        this.gid = gid;
        this.hid = hid;
        this.aid = aid;
        this.stadium = stadium;
        this.ticket_price = ticket_price;
        this.ticket_stock = ticket_stock;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public int getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getTicket_stock() {
        return ticket_stock;
    }

    public void setTicket_stock(int ticket_stock) {
        this.ticket_stock = ticket_stock;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gid='" + gid + '\'' +
                ", hid='" + hid + '\'' +
                ", aid='" + aid + '\'' +
                ", stadium='" + stadium + '\'' +
                ", ticket_price=" + ticket_price +
                ", ticket_stock=" + ticket_stock +
                '}';
    }
}
