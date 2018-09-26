package com.example.ares.buttonnavigation.Fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ares.buttonnavigation.Activity.DetailActivity
import com.example.ares.buttonnavigation.Adapter.MatchAdapter
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.NetworkService.ApiRepository
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.Utils.database
import com.example.ares.buttonnavigation.ankoUI.RecycleViewUI
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavoriteFragment:Fragment() {

    private var list: MutableList<Match> = mutableListOf()
    lateinit var adapter: MatchAdapter
    private var gson: Gson = Gson()
    private var apiRepository: ApiRepository = ApiRepository()
    lateinit var btnNav : BottomNavigationView
    lateinit var rvList: RecyclerView
    lateinit var tglIndo : TanggalIndo


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MatchAdapter(list){
            tglIndo = TanggalIndo()
            ctx.startActivity<DetailActivity>("data" to it)
        }
        rvList.adapter = adapter


    }
    private  fun showFavorite(){
        context?.database?.use {
            val hasil = select(Match.TABLE_FAVORITE)
            val favorite = hasil.parseList(classParser<Match>())
            list.addAll(favorite)
            adapter.notifyDataSetChanged()

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecycleViewUI<Fragment>().createView(AnkoContext.create(ctx, this))

    }
}