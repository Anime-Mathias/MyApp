package com.example.FrenchSoccerApp.controller;

import com.example.FrenchSoccerApp.model.LeagueModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FrenchSoccerAPI {
        @GET("search_all_teams.php")
        Call<LeagueModel> getListMatch (@Query("l") String league);
}