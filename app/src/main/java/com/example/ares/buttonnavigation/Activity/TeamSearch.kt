package com.example.ares.buttonnavigation.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.SearchView
import com.example.ares.buttonnavigation.Adapter.TeamAdapter
import com.example.ares.buttonnavigation.Fragment.MainView
import com.example.ares.buttonnavigation.Fragment.Presenter
import com.example.ares.buttonnavigation.Model.Leagues
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamSearch : AppCompatActivity(),MainView {


    override fun showIdLeague(data: List<Leagues>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeam(data: List<Team>) {
        list.clear()
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
      progressBar.visible()
    }

    override fun hideLoading() {
      progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Match>) {

    }

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var gson: Gson
    lateinit var TeamList: ArrayList<Team>
    lateinit var apiRepository: ApiRepository
    lateinit var presenter: Presenter
    lateinit var list: MutableList<Team>
    lateinit var adapter:TeamAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeamSearchViewUi().setContentView(this)
        val actionBar = supportActionBar
        actionBar?.title = "Search Football Match"
        progressBar = find(R.id.progressSearch)
        recyclerView = find(R.id.rvSearchView)
        gson = Gson()
        apiRepository = ApiRepository()
        TeamList = ArrayList()
        presenter = Presenter(this,gson,apiRepository)
        list = mutableListOf()
        adapter = TeamAdapter(list){
            ctx.startActivity<TeamDetail>("dataTeam" to it)
        }
        recyclerView.adapter =adapter
        progressBar.invisible()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view,menu)
        val searchItem = menu?.findItem(R.id.search_view)
        searchView = searchItem?.actionView as SearchView
        searchView.setQueryHint("Cari Team")
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val data = query!!
                presenter.searchAllTeam(data)
                recyclerView.adapter = adapter

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val data = newText!!
                presenter.searchAllTeam(data)
                recyclerView.adapter = adapter
               return true
            }

        })

        return true

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.search_view->{
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
class TeamSearchViewUi :AnkoComponent<TeamSearch>{


    override fun createView(ui: AnkoContext<TeamSearch>) = with(ui) {

            relativeLayout {
                recyclerView{
                    id = R.id.rvSearchView
                    layoutManager = LinearLayoutManager(context)

                }.lparams(matchParent, wrapContent)
               progressBar{
                    id = R.id.progressSearch
                }.lparams(wrapContent){
                    centerHorizontally()
                }
            }
        }

    }



