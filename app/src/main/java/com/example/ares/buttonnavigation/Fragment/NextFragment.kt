package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ares.buttonnavigation.*
import com.example.ares.buttonnavigation.Activity.DetailActivity
import com.example.ares.buttonnavigation.Adapter.PrevAdapter
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.MatchResponse
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class NextFragment : Fragment(),AnkoComponent<Context> {
    private var list: MutableList<Match> = mutableListOf()
    lateinit var adapter: PrevAdapter
    private var gson: Gson = Gson()
    private var apiRepository: ApiRepository = ApiRepository()
    lateinit var rvList:RecyclerView
    lateinit var tglIndo : TanggalIndo


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            rvList = recyclerView {
                id = R.id.rv_list
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height =
            wrapContent) {
                above(R.id.navigation)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PrevAdapter(list) {
            val toast = Toast.makeText(ctx, it.idEvent, Toast.LENGTH_SHORT)
            toast.show()
            tglIndo = TanggalIndo()
            ctx.startActivity<DetailActivity>("data" to it)
        }
        rvList.adapter = adapter
       match()

    }

    private fun match() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch()), MatchResponse::class.java)
            uiThread {
                list.clear()
                list.addAll(data.events)
                adapter.notifyDataSetChanged()

            }
        }

    }
}
