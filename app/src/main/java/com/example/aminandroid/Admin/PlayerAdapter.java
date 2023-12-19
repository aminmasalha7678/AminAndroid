package com.example.aminandroid.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

    List<Player> PlayersInfoList;
    Context context;

    public PlayerAdapter(Context context,List<Player> PlayersInfoList){
        this.context = context;
        this.PlayersInfoList = PlayersInfoList;
    }
    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        String name = "Name: " + PlayersInfoList.get(position).getName();
        String age = "Age: " + PlayersInfoList.get(position).getAge();
        String points = "Points: " + PlayersInfoList.get(position).getPoints();

        holder.PlayerName.setText(name);
        holder.PlayerAge.setText(age);
        holder.PlayerPoints.setText(points);
    }

    @Override
    public int getItemCount() {
        return PlayersInfoList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView PlayerName, PlayerPoints, PlayerAge;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            PlayerName = itemView.findViewById(R.id.list_player_name);
            PlayerAge = itemView.findViewById(R.id.list_player_age);
            PlayerPoints = itemView.findViewById(R.id.list_player_points);
        }
    }
}

