package com.example.aminandroid.ClientFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.aminandroid.PickPlayerOrTeamActivity;
import com.example.aminandroid.R;

public class GameFragment extends Fragment implements View.OnClickListener {


    Button teamBattle,playerBattle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        teamBattle = v.findViewById(R.id.game_team_battle);
        playerBattle = v.findViewById(R.id.game_one_vs_one);

        teamBattle.setOnClickListener(this);
        playerBattle.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == playerBattle.getId()){
            Intent i = new Intent(v.getContext(), PickPlayerOrTeamActivity.class);
            i.putExtra("info","player");
            startActivity(i);
        }
        else{
            Intent i = new Intent(v.getContext(), PickPlayerOrTeamActivity.class);
            i.putExtra("info","team");
            startActivity(i);
        }
    }
}