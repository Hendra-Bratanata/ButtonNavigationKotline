package com.example.ares.buttonnavigation.Adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ares.buttonnavigation.ankoUI.RecycleViewUI
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MatchAdapter(private val prevMatch : List<Match>, private val listener: (Match)-> Unit)
    : RecyclerView.Adapter<PrevMatchHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PrevMatchHolder {
        return PrevMatchHolder(RecycleViewUI<ViewGroup>().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = prevMatch.size

    override fun onBindViewHolder(p0: PrevMatchHolder, p1: Int) {
      p0.bindItem(prevMatch[p1],listener)

    }

}

class PrevMatchHolder(view : View):RecyclerView.ViewHolder(view) {
        private val home : TextView  = view.find(tvHome)
        private val homeScore : TextView = view.find(tvHomeScore)
        private val away : TextView = view.find(tvAway)
        private val awayScore : TextView = view.find(tvAwayScore)
        private val tgl : TextView = view.find(tglIndo)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(prevMatchs: Match, listener: (Match) -> Unit) {

        home.text = prevMatchs.HomeTeam
        homeScore.text = prevMatchs.HomeScore
        away.text = prevMatchs.AwayTeam
        awayScore.text = prevMatchs.AwayScore
        itemView.setOnClickListener {
            listener(prevMatchs)
        }


        val tanggal = TanggalIndo()
        var tglIndo =tanggal.ambilTanggal(prevMatchs.dateEvent)
        tgl.text = tglIndo
    }


}
