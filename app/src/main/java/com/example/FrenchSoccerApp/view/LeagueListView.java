package com.example.FrenchSoccerApp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.FrenchSoccerApp.R;
import com.example.FrenchSoccerApp.controller.Controller;
import com.example.FrenchSoccerApp.model.TeamDetailsModel;
import com.google.gson.Gson;

import java.util.List;

public class LeagueListView extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        controller = new Controller(this);
        context = getApplicationContext();
        controller.onCreate();
    }

    public void showList(List<TeamDetailsModel> listCompet) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SoccerAdapter(listCompet,new ListenerClickCompet());
        recyclerView.setAdapter(mAdapter);
    }

    public void refresh(View view) {
        controller.cachefunct(true);
        System.out.println("REFRESH");
    }

    public static Context getContext (){
        return context;
    }
    public class ListenerClickCompet implements View.OnClickListener {

        @Override
        public void onClick (View v){
            Intent intent = new Intent(LeagueListView.this, TeamDetailsView.class);
            int index = recyclerView.getChildLayoutPosition(v);
            Gson gson = new Gson();
            intent.putExtra("data",gson.toJson(controller.getCompet().getCompetitions().get(index)));
            startActivity(intent);
        }
    }
}