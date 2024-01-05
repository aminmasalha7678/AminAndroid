package com.example.aminandroid.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

    List<Player> PlayersInfoList;
    Context context;
    String pid;

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
        pid = PlayersInfoList.get(position).getPid();

        holder.PlayerName.setText(name);
        holder.PlayerAge.setText(age);
        holder.PlayerPoints.setText(points);
        holder.playerId = pid;
    }

    @Override
    public int getItemCount() {
        return PlayersInfoList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView PlayerName, PlayerPoints, PlayerAge;
        String playerId;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            PlayerName = itemView.findViewById(R.id.list_player_name);
            PlayerAge = itemView.findViewById(R.id.list_player_age);
            PlayerPoints = itemView.findViewById(R.id.list_player_points);
            playerId = "";
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), UpdateActivity.class);
            i.putExtra("id",playerId);
            i.putExtra("info","Player");
            v.getContext().startActivity(i);
        }
    }
}

