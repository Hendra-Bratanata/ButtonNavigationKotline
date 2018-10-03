package com.example.ares.buttonnavigation

import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.MatchResponse
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository:ApiRepository,
                           private val gson:Gson){


    fun getPrevMatchDetail(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPrevMatch()), MatchResponse::class.java)

        uiThread {
            view.hideLoading()
            view.showMatchDetail(data.events)
        }
        }
    }
}