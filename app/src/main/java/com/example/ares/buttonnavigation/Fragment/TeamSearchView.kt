package com.example.ares.buttonnavigation.Fragment

import com.example.ares.buttonnavigation.Model.Team

interface TeamSearchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Team>)

}