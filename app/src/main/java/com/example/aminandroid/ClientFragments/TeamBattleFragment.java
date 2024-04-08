package com.example.aminandroid.ClientFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.PickPlayerOrTeamActivity;
import com.example.aminandroid.R;

public class TeamBattleFragment extends Fragment implements View.OnClickListener {

    Button chooseTeams;
    ImageView team1Image,team2Image;
    TextView team1Text,team2Text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_battle, container, false);
        chooseTeams = v.findViewById(R.id.choose_teams_button);
        team1Image = v.findViewById(R.id.teamone_image);
        team1Text = v.findViewById(R.id.teamone_name);
        team2Image = v.findViewById(R.id.teamtwo_image);
        team2Text = v.findViewById(R.id.teamtwo_name);

        chooseTeams.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == chooseTeams.getId()){
            Intent i = new Intent(v.getContext(), PickPlayerOrTeamActivity.class);
            i.putExtra("info","team");
            startActivity(i);
        }
        else{

        }
    }
}