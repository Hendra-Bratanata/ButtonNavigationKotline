package com.example.ares.buttonnavigation

import com.example.ares.buttonnavigation.Model.Match

interface MatchDetailView{
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Match>)
}