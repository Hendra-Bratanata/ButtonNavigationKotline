package com.example.ares.buttonnavigation.Fragment

import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.PlayerResponse
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.example.ares.buttonnavigation.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val gson: Gson,
                      private val apiRepository: ApiRepository,
                      private val context : CoroutineContextProvider = CoroutineContextProvider()){


    fun getPlayer(club:String){
        view.showLoading()
        async (context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPlayer(club)),PlayerResponse::class.java)
            }
            view.hideLoading()
            view.showPlayerDetail(data.await().player)
        }
    }
}