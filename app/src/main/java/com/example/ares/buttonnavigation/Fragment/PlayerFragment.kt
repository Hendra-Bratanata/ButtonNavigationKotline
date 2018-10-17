package com.example.ares.buttonnavigation.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.ares.buttonnavigation.Activity.DetailPlayer
import com.example.ares.buttonnavigation.Adapter.PlayerAdapter
import com.example.ares.buttonnavigation.Model.Player
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class PlayerFragment: Fragment(),AnkoComponent<Context>,PlayerView{
    override fun showLoading() {
       progressBar.visible()
    }

    override fun hideLoading() {
      progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<Player>) {
        list.clear()
        list.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

    lateinit var gson: Gson
    lateinit var apiRepository: ApiRepository
    lateinit var presenter: PlayerPresenter
    lateinit var progressBar: ProgressBar
    lateinit var rvPlayerList:RecyclerView
    lateinit var list:MutableList<Player>
    lateinit var data:Team
    lateinit var playerAdapter: PlayerAdapter

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        relativeLayout{
            progressBar = progressBar().lparams{centerHorizontally()}

           rvPlayerList =  recyclerView {
                id= R.id.rv_Player
                layoutManager = LinearLayoutManager(activity)

            }.lparams(matchParent, matchParent)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var namaClub = data.namaTeam.toString()
        gson = Gson()
        apiRepository = ApiRepository()
        list = mutableListOf()

        playerAdapter = PlayerAdapter(this.context!!,list){
            ctx.startActivity<DetailPlayer>("dataPlayer" to it)

        }
        rvPlayerList.adapter = playerAdapter
        presenter = PlayerPresenter(this,gson,apiRepository)
        presenter.getPlayer(namaClub)
    }

}