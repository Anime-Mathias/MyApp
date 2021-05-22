package com.example.FrenchSoccerApp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.FrenchSoccerApp.view.LeagueListView;
import com.example.FrenchSoccerApp.model.TeamDetailsModel;
import com.example.FrenchSoccerApp.model.LeagueModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private LeagueModel listcompet;
    private LeagueListView view;
    private SharedPreferences share;
    private Gson gson;
    private FrenchSoccerAPI restApi;

    public Controller(LeagueListView leagueListView) {
        this.view = leagueListView;
    }
    public void onCreate() {

         gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restApi = retrofit.create(FrenchSoccerAPI.class);
        share = LeagueListView.getContext().getSharedPreferences("cache", Context.MODE_PRIVATE);
        cachefunct(false);
        }

    public LeagueModel getCompet(){
        return  listcompet;
    }
    public void cachefunct (boolean cache){

        if (share.contains("club") & cache ){
            String listclub = share.getString("club", "");
            Type listType = new TypeToken<ArrayList<TeamDetailsModel>>(){}.getType();
            List<TeamDetailsModel>  compet = gson.fromJson(listclub,listType);
            listcompet = new LeagueModel();
            listcompet.setTeams(compet);
            view.showList(compet);
        }

        else {
            Call<LeagueModel> call = restApi.getListMatch("French Ligue 1");
            call.enqueue(new Callback<LeagueModel>() {
                @Override
                public void onResponse(Call<LeagueModel> call, Response<LeagueModel> response) {
                    listcompet = response.body();
                    List<TeamDetailsModel>  compet = listcompet.getCompetitions();
                    view.showList(compet);
                    share.edit()
                            .putString("club",gson.toJson(compet))
                            .apply();
                }

                @Override
                public void onFailure(Call<LeagueModel> call, Throwable t) {
                    Log.d("ERROR", "Api Error");
                }
            });
        }
    }
}