package com.example.ares.buttonnavigation.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.anko.RecycleViewUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavoriteTeamsAdapter(private val favorite: List<Match>, private val listener:(Match) -> Unit)
    :RecyclerView.Adapter<FavoriteViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(RecycleViewUI<ViewGroup>().createView(AnkoContext.create(p0.context,p0)))


    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)
    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val home : TextView  = view.find(R.id.tvHome)
    private val away : TextView = view.find(R.id.tvAway)

    fun bindItem(fav : Match,listener: (Match) -> Unit){
        home.text = fav.HomeTeam
        away.text = fav.AwayTeam
        itemView.setOnClickListener {
            listener(fav)
        }
    }

}
