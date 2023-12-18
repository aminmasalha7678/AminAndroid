package com.example.aminandroid.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aminandroid.Admin.AdminFragments.AddPlayerFragment;
import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

    List<Player> Players;
    Context context;


    public PlayerAdapter(Context context,List<Player> Players){
        this.context = context;
        this.Players = Players;
    }
    @Override
    public PlayerViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return Players.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView PlayerName, PlayerPoints, PlayerChampionShips;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            PlayerName = (TextView) itemView.findViewById(R.id.list_player_name);
            PlayerPoints = (TextView) itemView.findViewById(R.id.list_player_champion);
            PlayerChampionShips = (TextView) itemView.findViewById(R.id.list_player_points);
        }
    }
}

