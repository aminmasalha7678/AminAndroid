package com.example.aminandroid.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.PickPlayerOrTeamActivity;
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
        String name = PlayersInfoList.get(position).getName();
        String pac = String.valueOf(PlayersInfoList.get(position).getPace());
        String sho = String.valueOf(PlayersInfoList.get(position).getShooting());
        String pas = String.valueOf(PlayersInfoList.get(position).getPassing());
        String dri = String.valueOf(PlayersInfoList.get(position).getDribbling());
        String def = String.valueOf(PlayersInfoList.get(position).getDefense());
        String phy = String.valueOf(PlayersInfoList.get(position).getPhysical());
        String overall = String.valueOf(PlayersInfoList.get(position).getOverall());
        String pos = PlayersInfoList.get(position).getPosition();

        pid = PlayersInfoList.get(position).getPid();

        holder.playerName.setText(name);
        holder.playerPac.setText(pac);
        holder.playerSho.setText(sho);
        holder.playerPas.setText(pas);
        holder.playerDri.setText(dri);
        holder.playerDef.setText(def);
        holder.playerPhy.setText(phy);
        holder.playerPos.setText(pos);
        holder.playerOverall.setText(overall);
        holder.playerId = pid;
    }

    @Override
    public int getItemCount() {
        return PlayersInfoList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout constraintLayout;
        ImageView card;
        TextView playerName,playerPac,playerSho,playerPas,playerDri,playerDef,playerPhy,playerPos,playerOverall;
        String playerId;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.card_layout);
            card = itemView.findViewById(R.id.card);
            playerName = itemView.findViewById(R.id.player_card_name);
            playerPac = itemView.findViewById(R.id.player_card_pac);
            playerSho = itemView.findViewById(R.id.player_card_sho);
            playerPas = itemView.findViewById(R.id.player_card_pas);
            playerDri = itemView.findViewById(R.id.player_card_dri);
            playerDef = itemView.findViewById(R.id.player_card_def);
            playerPhy = itemView.findViewById(R.id.player_card_phy);
            playerPos = itemView.findViewById(R.id.player_card_pos);
            playerOverall = itemView.findViewById(R.id.player_card_overall);
            playerId = "";


            itemView.setOnClickListener(this);

            if(itemView.getContext() instanceof PickPlayerOrTeamActivity) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(card.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 340);
                constraintSet.applyTo(constraintLayout);
            }

        }

        @Override
        public void onClick(View v) {
            if(itemView.getContext() instanceof AdminActivity) {
                Intent i = new Intent(v.getContext(), UpdateActivity.class);
                i.putExtra("id", playerId);
                i.putExtra("info", "Player");
                v.getContext().startActivity(i);
            }
            else if(itemView.getContext() instanceof PickPlayerOrTeamActivity){
                Intent intent = new Intent("message_subject_intent");
                intent.putExtra("player" ,playerId);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }

        }
    }
}

