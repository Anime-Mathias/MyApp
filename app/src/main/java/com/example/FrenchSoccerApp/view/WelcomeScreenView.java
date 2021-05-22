package com.example.FrenchSoccerApp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;

import com.example.FrenchSoccerApp.R;

public class WelcomeScreenView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void random(View view) {
        Intent randomIntent = new Intent(this, LeagueListView.class);
        startActivity(randomIntent);
    }
}