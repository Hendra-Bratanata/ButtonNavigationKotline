package com.example.ares.buttonnavigation.NetWorkService

import android.util.Log
import com.example.ares.buttonnavigation.BuildConfig

object TheSportDBAPI {

    fun searchAllTeam(club: String):String{
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/searchteams.php?t="+club
    }

    fun getPlayer(club: String):String{
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/searchplayers.php?t="+club
    }

    fun getTeam(liga:String):String{
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/search_all_teams.php?l="+liga
    }

    fun getidLeague():String{
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/all_leagues.php"
    }
    fun getPrevMatch(liga:String): String {
        var Url:String = BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/eventspastleague.php?id="+liga
        Log.d("GetPrev",Url)
        return Url

    }

    fun getNextMatch(liga:String): String {
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/eventsnextleague.php?id="+liga
    }
    fun getNextMatchDetail(teamId:String?): String {
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/lookupteam.php?id= $teamId"
    }
    fun searchMatch(data:String): String {
        return BuildConfig.BASE_URL+
                "api/v1/json/${BuildConfig.TSBD_API_KEY}"+"/searchevents.php?e="+data
    }
}