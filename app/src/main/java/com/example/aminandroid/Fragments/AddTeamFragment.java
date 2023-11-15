package com.example.aminandroid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aminandroid.Admin.AdminActivity;
import com.example.aminandroid.Classes.Player;
import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTeamFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText teamName,teamYear,teamChampion;
    Button addTeam;
    private DatabaseReference mDatabase;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTeamFragment newInstance(String param1, String param2) {
        AddTeamFragment fragment = new AddTeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_add_team, container, false);

        teamName = (EditText) v.findViewById(R.id.addteam_name);
        teamYear = (EditText) v.findViewById(R.id.addteam_year);
        teamChampion = (EditText) v.findViewById(R.id.addteam_championships);
        addTeam = (Button) v.findViewById(R.id.addteam_button);
        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        addTeam.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addteam_button){

            Team team = new Team(teamName.getText().toString(),Integer.parseInt(teamChampion.getText().toString()),Integer.parseInt(teamYear.getText().toString()));
            mDatabase.child("Teams").push().setValue(team);
            Toast.makeText(getContext(),"Team Added Succesfully",Toast.LENGTH_SHORT).show();
            teamName.setText("");
            teamChampion.setText("");
            teamYear.setText("");

        }
    }

}