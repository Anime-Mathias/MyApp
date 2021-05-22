package com.example.FrenchSoccerApp.model;
import java.util.List;

public class LeagueModel {
    List<TeamDetailsModel> teams;

    public List<TeamDetailsModel> getCompetitions() {
        return teams;
    }
    public void setTeams(List<TeamDetailsModel> teams) {
        this.teams = teams;
    }
}