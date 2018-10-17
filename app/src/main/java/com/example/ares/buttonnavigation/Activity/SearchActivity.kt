package com.example.ares.buttonnavigation.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.SearchView
import com.example.ares.buttonnavigation.Adapter.MatchAdapter
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

class SearchActivity : AppCompatActivity(),MainView {
    override fun showTeam(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
      progressBar.visible()
    }

    override fun hideLoading() {
      progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Match>) {
        matchList.clear()
        list.clear()
        for (i in data.indices){
            if (data[i].sport.equals("Soccer")){
            var match : Match = data[i]
              matchList.add(match)
                list.addAll(matchList)
            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun showIdLeague(data: List<Leagues>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var gson: Gson
    lateinit var matchList: ArrayList<Match>
    lateinit var apiRepository: ApiRepository
    lateinit var presenter: Presenter
    lateinit var list: MutableList<Match>
    lateinit var adapter:MatchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchViewUi().setContentView(this)
        val actionBar = supportActionBar
        actionBar?.title = ""

        progressBar = find(R.id.progressSearch)
        recyclerView = find(R.id.rvSearchView)
        gson = Gson()
        apiRepository = ApiRepository()
        matchList = ArrayList()
        presenter = Presenter(this,gson,apiRepository)
        list = mutableListOf()
        adapter = MatchAdapter(list){
            ctx.startActivity<DetailActivity>("data" to it)
        }
        recyclerView.adapter =adapter
        progressBar.invisible()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view,menu)
        val searchItem = menu?.findItem(R.id.search_view)
        searchView = searchItem?.actionView as SearchView
        searchView.setQueryHint("Cari Event")
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val data = query!!
                presenter.searchMatch(data)
                recyclerView.adapter = adapter

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val data = newText!!
                presenter.searchMatch(data)
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
internal class SearchViewUi:AnkoComponent<SearchActivity>{


    override fun createView(ui: AnkoContext<SearchActivity>) = with(ui) {

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


