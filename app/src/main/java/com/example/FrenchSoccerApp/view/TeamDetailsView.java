package com.example.FrenchSoccerApp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.FrenchSoccerApp.R;
import com.example.FrenchSoccerApp.model.TeamDetailsModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class TeamDetailsView extends Activity {

    private TeamDetailsModel data;
    public TextView leagueName;
    public TextView teamName;
    public ImageView teamIcon;
    public TextView managerName;
    public TextView stadiumName;
    public ImageView stadiumImage;
    public TextView maxSeatsNumber;
    public TextView stadiumLocal;
    public TextView jerseyText;
    public ImageView jerseyImage;
    public ImageView bannerImage;
    public ImageView ban2Image;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Gson gson = new Gson();
        Intent intent = getIntent();
        data = gson.fromJson(intent.getStringExtra("data"), TeamDetailsModel.class);
        leagueName = findViewById(R.id.league_name);
        teamName = findViewById(R.id.team);
        teamIcon = findViewById(R.id.icon);
        managerName = findViewById(R.id.manager_name);
        stadiumName = findViewById(R.id.stadium_name);
        stadiumImage = findViewById(R.id.stadium_ima);
        maxSeatsNumber = findViewById(R.id.stadium_capacity);
        stadiumLocal = findViewById(R.id.stadium_location);
        jerseyText = findViewById(R.id.jersey);
        jerseyImage = findViewById(R.id.jersey_ima);
        bannerImage = findViewById(R.id.banner);
        String name = data.getStrAlternate();
        final String compet = data.getStrLeague();
        final String manager = data.getStrManager();
        final int capa = data.getIntStadiumCapacity();
        String capas = Integer.toString(capa);
        final String loca = data.getStrStadiumLocation();
        final String stadium = data.getStrStadium();

        if(name == ""){
            name = data.getStrTeam();
        }

        if(stadium == null){
            capas = "";
        }

        teamName.setText(name);
        leagueName.setText(compet);
        stadiumLocal.setText(loca);
        maxSeatsNumber.setText(capas);
        stadiumName.setText(stadium);
        managerName.setText(manager);

        Picasso
                .get()
                .load(data.getStrTeamBadge())
                //.placeholder(R.drawable.default_icon)
                .resize(0,180)
                .into(teamIcon);

        if(data.getStrStadiumThumb() != null) {
            Picasso
                    .get()
                    .load(data.getStrTeamBanner())
                    //.placeholder(R.drawable.default_icon)
                    .resize(0, 180)
                    .into(bannerImage);
        }

        if(data.getStrStadiumThumb() != null){
            Picasso
                    .get()
                    .load(data.getStrStadiumThumb())
                    //.placeholder(R.drawable.default_icon)
                    .resize(0, 400)
                    .into(this.stadiumImage);
        }
        else {
            stadiumLocal.setText("");
        }

        if(data.getStrTeamJersey() != null){

            Picasso
                    .get()
                    .load(data.getStrTeamJersey())
                    //.placeholder(R.drawable.default_icon)
                    .resize(0, 300)
                    .into(jerseyImage);
        }
        else {
            jerseyText.setText("");
        }
    }
}