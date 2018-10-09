package com.example.ares.buttonnavigation.anko

import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.MatchResponse
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class Presenter(private val view: MainView,
                private val gson: Gson,
                private val apiRepository: ApiRepository){

    fun getPrevMatch(){
        view.showLoading()
        async(UI) {
            val data = bg {

                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPrevMatch()), MatchResponse::class.java)

            }
            view.hideLoading()
            view.showMatchDetail(data.await().events)

        }
    }
    fun getNextMatch(){
        view.showLoading()
        async(UI) {
            val data = bg {

                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch()), MatchResponse::class.java)

            }
            view.hideLoading()
            view.showMatchDetail(data.await().events)

        }
    }
}