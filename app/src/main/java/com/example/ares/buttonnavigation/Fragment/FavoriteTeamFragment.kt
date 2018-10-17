package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.ares.buttonnavigation.Activity.TeamDetail
import com.example.ares.buttonnavigation.Adapter.TeamAdapter
import com.example.ares.buttonnavigation.Database.databaseTeam
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamFragment : Fragment(),AnkoComponent<Context>{

    private var TeamFavorite: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var listEvent: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<Context>)= with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)



            swipeRefreshLayout = swipeRefreshLayout {

                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)


                    listEvent = recyclerView {
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = wrapContent)

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }

                }

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TeamAdapter(TeamFavorite){
            ctx.startActivity<TeamDetail>("dataTeam" to it)

        }
        listEvent.adapter =adapter
        showFavorite()
        swipeRefreshLayout.onRefresh {
            showFavorite()
        }
    }

    private fun showFavorite(){
        var favorite :List<Team>
        async(UI){
                progressBar.visible()
                context?.databaseTeam?.use {
                    val result = select(Team.TABEL_Team)
                    favorite = result.parseList(classParser())
                    swipeRefreshLayout.isRefreshing=false
                   TeamFavorite.clear()
                    TeamFavorite.addAll(favorite)
                    progressBar.invisible()
                    adapter.notifyDataSetChanged()
            }

        }
        }
    }
