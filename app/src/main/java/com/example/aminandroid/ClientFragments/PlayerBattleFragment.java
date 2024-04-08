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

public class PlayerBattleFragment extends Fragment implements View.OnClickListener {

    Button choosePlayers;
    ImageView player1Image,player2Image;
    TextView player1Text,player2Text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_battle, container, false);
        choosePlayers = v.findViewById(R.id.choose_players_button);
        player1Image = v.findViewById(R.id.playerone_image);
        player1Text = v.findViewById(R.id.playerone_name);
        player2Image = v.findViewById(R.id.playertwo_image);
        player2Text = v.findViewById(R.id.playertwo_name);

        choosePlayers.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == choosePlayers.getId()){
            Intent i = new Intent(v.getContext(), PickPlayerOrTeamActivity.class);
            i.putExtra("info","player");
            startActivity(i);
        }
        else{

        }
    }
}