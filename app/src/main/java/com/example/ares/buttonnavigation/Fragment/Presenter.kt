package com.example.ares.buttonnavigation.Fragment

import android.util.Log
import com.example.ares.buttonnavigation.NetWorkService.*
import com.example.ares.buttonnavigation.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class Presenter(private val view: MainView,
                private val gson: Gson,
                private val apiRepository: ApiRepository, private val context : CoroutineContextProvider = CoroutineContextProvider()){
    fun searchAllTeam(liga:String){
        view.showLoading()
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.searchAllTeam(liga)),TeamRespon::class.java)
            }
            view.hideLoading()
            view.showTeam(data.await().teams)
        }
    }

    fun getTeam(liga:String){
        view.showLoading()
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getTeam(liga)),TeamRespon::class.java)
            }
            view.hideLoading()
            view.showTeam(data.await().teams)
        }
    }


    fun getIdLeague(){
        Log.d("getId","Running")
        view.showLoading()
        async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getidLeague()),LeagueResponse::class.java)
            }
            view.hideLoading()
            view.showIdLeague(data.await().leagues)
        }
    }

    fun getPrevMatch(liga:String){
        Log.d("getPre","On running")
        Log.d("onRunning",liga)
        view.showLoading()
        async(context.main) {
            val data = bg {

                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPrevMatch(liga)), MatchResponse::class.java)

            }
            view.hideLoading()
            view.showMatchDetail(data.await().events)

        }
    }
    fun getNextMatch(liga:String){
        view.showLoading()
        async(context.main) {
            val data = bg {

                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch(liga)), MatchResponse::class.java)

            }
            view.hideLoading()
            view.showMatchDetail(data.await().events)

        }
    }
    fun searchMatch(data:String){
        view.showLoading()
        async(context.main) {
            val data = bg {

                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.searchMatch(data)), SearchResponse::class.java)

            }
            view.hideLoading()
            view.showMatchDetail(data.await().event)

        }
    }
}