package com.example.ares.buttonnavigation.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.anko.TeamUi
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class TeamAdapter(private val teams:List<Team>,private val listener:(Team)->Unit)
    :RecyclerView.Adapter<TeamHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamHolder {
        return  TeamHolder(TeamUi<ViewGroup>().createView(AnkoContext.create(p0.context,p0)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: TeamHolder, p1: Int) {
    p0.bindItem(teams[p1],listener)

    }

}

class TeamHolder(view: View):RecyclerView.ViewHolder(view) {

    private val badge : ImageView = view.find(R.id.imgTeam)
    private val name :TextView = view.find(R.id.tv_namaTeam)

    fun bindItem(team:Team, listener: (Team) -> Unit){
        name.text = team.namaTeam

        Picasso.get().load(team.teamBadge).into(badge)
        itemView.setOnClickListener {
            listener(team)
        }
    }


}
