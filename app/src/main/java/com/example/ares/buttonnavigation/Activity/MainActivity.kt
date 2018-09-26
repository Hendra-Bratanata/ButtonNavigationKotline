package com.example.ares.buttonnavigation.Activity


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.Data.MatchResponse
import com.example.ares.buttonnavigation.NetworkService.ApiRepository
import com.example.ares.buttonnavigation.NetworkService.TheSportDBAPI
import com.example.ares.buttonnavigation.Adapter.MatchAdapter
import com.example.ares.buttonnavigation.Fragment.FavoriteFragment
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var list: MutableList<Match> = mutableListOf()
    lateinit var adapter: MatchAdapter
    private var gson: Gson = Gson()
    private var apiRepository: ApiRepository = ApiRepository()
    lateinit var btnNav : BottomNavigationView
    lateinit var rvList:RecyclerView
    lateinit var tglIndo : TanggalIndo

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                prevMatch()
                val action = supportActionBar
                action!!.setTitle("Prev.Match")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val action = supportActionBar
                action!!.setTitle("Next.Match")
                nextMatch()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                favoriteFrag()
            }
        }
        false
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val action = supportActionBar
        action!!.setTitle("Home")
        relativeLayout {
            id = R.id.container


            rvList = recyclerView {
                id = R.id.rv_list
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height =
            wrapContent) {
                above(R.id.navigation)
            }
            view ().lparams(matchParent,dip(4)){
                backgroundDrawable=ContextCompat.getDrawable(ctx, R.drawable.shadows)
                above(R.id.navigation)
            }
           btnNav = bottomNavigationView {
                id = R.id.navigation
                backgroundColor = Color.WHITE
               itemIconTintList =ContextCompat.getColorStateList(ctx, R.drawable.nav_item_color)
               itemTextColor=ContextCompat.getColorStateList(ctx, R.drawable.nav_item_color)
                inflateMenu(R.menu.navigation)
            }.lparams(width = matchParent) {
                alignParentBottom()
                marginEnd = 0
                marginStart = 0
            }
        }

        Log.d("tag", "navigation")
        btnNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        adapter = MatchAdapter(list) {
            tglIndo = TanggalIndo()
            startActivity<DetailActivity>("data" to it)
        }
        rvList.adapter = adapter
        prevMatch()

    }

    fun prevMatch() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPrevMatch()), MatchResponse::class.java)
            uiThread {
                Log.d("log", data.events.toString())
                list.clear()
                list.addAll(data.events)
                adapter.notifyDataSetChanged()

            }
        }
    }

    fun nextMatch() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch()), MatchResponse::class.java)
            uiThread {
                Log.d("log", data.events.toString())
                list.clear()
                list.addAll(data.events)
                adapter.notifyDataSetChanged()

            }
        }

    }
    fun favoriteFrag(){

            supportFragmentManager.beginTransaction().replace(R.id.rv_list,FavoriteFragment()).commit()

    }
}
