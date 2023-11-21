package com.example.aminandroid.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aminandroid.Classes.Team;
import com.example.aminandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlayerFragment extends Fragment implements View.OnClickListener, ValueEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText playerName, playerAge, playerMvps, playerChampions, playerPoints;
    Button addPlayer;
    Spinner playerTeam;
    DatabaseReference mDatabase;
    ArrayList<String> teamNames;

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPlayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPlayerFragment newInstance(String param1, String param2) {
        AddPlayerFragment fragment = new AddPlayerFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_player, container, false);

        mDatabase = FirebaseDatabase.getInstance("https://aminandroid-45afc-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        playerName = (EditText) v.findViewById(R.id.addplayer_name);
        playerAge = (EditText) v.findViewById(R.id.addplayer_age);
        playerChampions = (EditText) v.findViewById(R.id.addplayer_champions);
        playerPoints = (EditText) v.findViewById(R.id.addplayer_points);
        playerMvps = (EditText) v.findViewById(R.id.addplayer_mvps);
        playerTeam = (Spinner) v.findViewById(R.id.addplayer_team);
        addPlayer = (Button) v.findViewById(R.id.addplayer_button);


        addPlayer.setOnClickListener(this);

        teamNames = new ArrayList<String>();
        mDatabase.child("Teams").addValueEventListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, teamNames);
        playerTeam.setAdapter(adapter);
        playerTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedValue = teamNames.get(position);
                compare(selectedValue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addplayer_button) {

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        teamNames.clear();
        for (DataSnapshot ds : snapshot.getChildren()) {
            String name = String.valueOf(ds.child(snapshot.getKey()).child("name"));
            teamNames.add(name);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    public void compare(String selectedValue) {
        mDatabase.child("Teams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child(snapshot.getKey()).child("name").equals(selectedValue)) {
                        String Tid = String.valueOf(ds.child(snapshot.getKey()).child("tid"));
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}