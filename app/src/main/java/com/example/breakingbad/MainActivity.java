package com.example.breakingbad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CharacterAPITask.CharacterListener {

    private final String TAG = getClass().getSimpleName();
    private List<Character> mCharacters = new ArrayList<Character>();
    private RecyclerView mCharactersRecyclerView;
    private CharacterAdapter mCharacterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate was called");

        mCharactersRecyclerView = (RecyclerView) findViewById(R.id.characters_recycler_view);
        //Set layout manager to recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCharactersRecyclerView.setLayoutManager(layoutManager);

        mCharacterAdapter = new CharacterAdapter(mCharacters);
        //Link adapter to recycler view
        mCharactersRecyclerView.setAdapter(mCharacterAdapter);

        String[] params = {
                "https://www.breakingbadapi.com/api/characters"
        };

        new CharacterAPITask(this).execute(params);


    }

    @Override
    public void onCharactersAvailable(List<Character> characters) {
        Log.d(TAG, "We have " + characters.size() + " items.");

        this.mCharacters.clear();
        this.mCharacters.addAll(characters);
        this.mCharacterAdapter.notifyDataSetChanged();
    }
}