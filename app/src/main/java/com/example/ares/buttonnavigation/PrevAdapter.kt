package com.example.ares.buttonnavigation

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import anko.RecycleViewUI
import com.example.ares.buttonnavigation.R.id.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.text.SimpleDateFormat

class PrevAdapter(private val prevMatch : List<PrevMatch>,private val listener: (PrevMatch)-> Unit)
    : RecyclerView.Adapter<PrevMatchHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PrevMatchHolder {
        return PrevMatchHolder(RecycleViewUI<ViewGroup>().createView(AnkoContext.create(p0.context,p0)))
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
    fun bindItem(prevMatchs: PrevMatch, listener: (PrevMatch) -> Unit) {

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
