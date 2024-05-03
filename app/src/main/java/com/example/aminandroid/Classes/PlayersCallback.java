package com.example.aminandroid.Classes;

import java.util.ArrayList;

public interface PlayersCallback {
    void onCallback(ArrayList<Player> players);
    void onCallback(Player[] players);
    void onGameStart(int[] scores);
}
