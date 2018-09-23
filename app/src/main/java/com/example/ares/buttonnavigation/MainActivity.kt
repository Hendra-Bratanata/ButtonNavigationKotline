package com.example.ares.buttonnavigation


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var list: MutableList<PrevMatch> = mutableListOf()
    lateinit var adapter: PrevAdapter
    private var gson: Gson = Gson()
    private var apiRepository: ApiRepository = ApiRepository()
    lateinit var btnNav : BottomNavigationView
    lateinit var rvList:RecyclerView
    lateinit var tglIndo :TanggalIndo

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                asy()
                val action = supportActionBar
                action!!.setTitle("Prev.Match")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val action = supportActionBar
                action!!.setTitle("Next.Match")
                Nextasy()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

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

           btnNav = bottomNavigationView {
                id = R.id.navigation
                backgroundColor = Color.WHITE
                inflateMenu(R.menu.navigation)
            }.lparams(width = matchParent) {
                alignParentBottom()
                marginEnd = 0
                marginStart = 0
            }
        }

        Log.d("tag", "navigation")
        btnNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        adapter = PrevAdapter(list){
            val toast = Toast.makeText(applicationContext,it.idEvent,Toast.LENGTH_SHORT)
            toast.show()
            tglIndo = TanggalIndo()
            startActivity<DetaiActivity>("data" to it)
        }
        rvList.adapter = adapter
        asy()

    }

    fun asy() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getPrevMatch()), PrevMatchResponse::class.java)
            uiThread {
                Log.d("log", data.events.toString())
                list.clear()
                list.addAll(data.events)
                adapter.notifyDataSetChanged()

            }
        }
    }

    fun Nextasy() {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatch()), PrevMatchResponse::class.java)
            uiThread {
                Log.d("log", data.events.toString())
                list.clear()
                list.addAll(data.events)
                adapter.notifyDataSetChanged()

            }
        }

    }
}
