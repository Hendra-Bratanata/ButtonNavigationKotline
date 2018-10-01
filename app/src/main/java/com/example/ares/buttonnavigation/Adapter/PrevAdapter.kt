package com.example.ares.buttonnavigation.Adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import anko.RecycleViewUI
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PrevAdapter(private val matches : List<Match>, private val listener: (Match)-> Unit)
    : RecyclerView.Adapter<MatchHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchHolder {
        return MatchHolder(RecycleViewUI<ViewGroup>().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(p0: MatchHolder, p1: Int) {
      p0.bindItem(matches[p1],listener)

    }

}

class MatchHolder(view : View):RecyclerView.ViewHolder(view) {
        private val home : TextView  = view.find(tvHome)
        private val homeScore : TextView = view.find(tvHomeScore)
        private val away : TextView = view.find(tvAway)
        private val awayScore : TextView = view.find(tvAwayScore)
        private val tgl : TextView = view.find(tglIndo)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(matchs: Match, listener: (Match) -> Unit) {

        home.text = matchs.HomeTeam
        homeScore.text = matchs.HomeScore
        away.text = matchs.AwayTeam
        awayScore.text = matchs.AwayScore
        itemView.setOnClickListener {
            listener(matchs)
        }


        val tanggal = TanggalIndo()
        var tglIndo =tanggal.ambilTanggal(matchs.dateEvent)
        tgl.text = tglIndo
    }


}
