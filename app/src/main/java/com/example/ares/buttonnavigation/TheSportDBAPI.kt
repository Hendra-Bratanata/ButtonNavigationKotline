package com.example.ares.buttonnavigation

import android.net.Uri
import android.util.Log

object TheSportDBAPI {
    fun getPrevMatch(): String {
        val prev: String = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSBD_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", "4328")
                .build()
                .toString()
        Log.d("log", prev)
        return prev
    }

    fun getNextMatch(): String {
        val prev: String = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSBD_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", "4328")
                .build()
                .toString()
        Log.d("log1", prev)
//        val urll :String = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        return prev
    }
    fun getNextMatchDetail(teamId:String?): String {
        val prev: String = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSBD_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
        Log.d("log", prev)
//        val urll :String = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        return prev
    }
}