package com.example.aminandroid.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.PickPlayerOrTeamActivity;
import com.example.aminandroid.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{

    List<Team> TeamInfoList;
    Context context;
    String tid;

    public TeamAdapter(Context context,List<Team> TeamInfoList){
        //defines the context of the adapter and sets the info of the teams up for later use
        this.context = context;
        this.TeamInfoList = TeamInfoList;
    }
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //shows an item aka team card
        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new TeamViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {

        //defines each teams attributes
        String name = "Name: " + TeamInfoList.get(position).getName();
        String establishment = "Establishment: " + TeamInfoList.get(position).getEstablishment();
        String championships = "Championships: " + TeamInfoList.get(position).getChampionships();

        tid = TeamInfoList.get(position).getTid();
        //sets each teams attributes to the corresponding place in the team card
        holder.TeamName.setText(name);
        holder.TeamChampionships.setText(establishment);
        holder.TeamEstablishment.setText(championships);
        holder.TeamId = tid;
    }

    @Override
    public int getItemCount() {
        return TeamInfoList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TeamName,TeamChampionships,TeamEstablishment;
        String TeamId;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            // define every view in the team_card xml
            TeamName = itemView.findViewById(R.id.list_player_name);
            TeamChampionships = itemView.findViewById(R.id.list_player_age);
            TeamEstablishment = itemView.findViewById(R.id.list_player_points);
            TeamId = "";

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemView.getContext() instanceof AdminActivity) {
                //sends the user to updateActivity to update the teams info
                Intent i = new Intent(v.getContext(), UpdateActivity.class);
                i.putExtra("id", TeamId);
                i.putExtra("info", "Team");
                v.getContext().startActivity(i);
            }
             else if(itemView.getContext() instanceof PickPlayerOrTeamActivity){
                //sends to PickPlayerOrTeamActivity the teams id
                Intent intent = new Intent("message_subject_intent");
                intent.putExtra("team" ,TeamId);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        }
    }
}

