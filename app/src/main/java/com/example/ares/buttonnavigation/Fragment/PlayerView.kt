package com.example.ares.buttonnavigation.Fragment

import com.example.ares.buttonnavigation.Model.Player

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Player>)

}