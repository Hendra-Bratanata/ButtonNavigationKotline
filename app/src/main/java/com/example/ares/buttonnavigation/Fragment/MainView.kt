package com.example.ares.buttonnavigation.Fragment

import com.example.ares.buttonnavigation.Model.Leagues
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.Model.Team

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Match>)
    fun showIdLeague(data: List<Leagues>)
    fun showTeam(data: List<Team>)
}