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

        View view = LayoutInflater.from(context).inflate(R.layout.player_card, parent, false);
        return new PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        String name = "Name: " + PlayersInfoList.get(position).getName();
        String pac = "Pace: " + PlayersInfoList.get(position).getName();
        String sho = "Shooting: " + PlayersInfoList.get(position).getName();
        String pas = "Passing: " + PlayersInfoList.get(position).getName();
        String dri = "Dribbling: " + PlayersInfoList.get(position).getName();
        String def = "Defense: " + PlayersInfoList.get(position).getName();
        String phy = "Physical: " + PlayersInfoList.get(position).getName();
        String pos = "Positions: " + PlayersInfoList.get(position).getName();

        pid = PlayersInfoList.get(position).getPid();

        holder.playerName.setText(name);
        holder.playerPac.setText(pac);
        holder.playerSho.setText(sho);
        holder.playerPas.setText(pas);
        holder.playerDri.setText(dri);
        holder.playerDef.setText(def);
        holder.playerPhy.setText(phy);
        holder.playerPos.setText(pos);
        holder.playerId = pid;
    }

    @Override
    public int getItemCount() {
        return PlayersInfoList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView playerName, playerPac,playerSho,playerPas,playerDri,playerDef,playerPhy,playerPos ;
        String playerId;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_card_name);
            playerPac = itemView.findViewById(R.id.player_card_pac);
            playerSho = itemView.findViewById(R.id.player_card_sho);
            playerPas = itemView.findViewById(R.id.player_card_pas);
            playerDri = itemView.findViewById(R.id.player_card_dri);
            playerDef = itemView.findViewById(R.id.player_card_def);
            playerPhy = itemView.findViewById(R.id.player_card_phy);
            playerPos = itemView.findViewById(R.id.player_card_pos);
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

