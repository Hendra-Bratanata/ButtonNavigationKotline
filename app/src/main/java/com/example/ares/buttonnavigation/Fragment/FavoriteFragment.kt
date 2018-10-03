package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ares.buttonnavigation.Activity.DetailActivity
import com.example.ares.buttonnavigation.Adapter.PrevAdapter
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.Database.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class FavoriteFragment : Fragment(),AnkoComponent<Context>{

    private var favorites: MutableList<Match> = mutableListOf()
    private lateinit var adapter: PrevAdapter
    private lateinit var listEvent: RecyclerView

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
                listEvent = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PrevAdapter(favorites){
            ctx.startActivity<DetailActivity>("data" to it)
        }
        listEvent.adapter =adapter
        showFavorite()
    }

    private fun showFavorite(){
        context?.database?.use {
            val result = select(Match.TABEL_FAVORITE)
            val favorite = result.parseList(classParser<Match>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}