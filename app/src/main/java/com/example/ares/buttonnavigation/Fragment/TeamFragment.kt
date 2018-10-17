package com.example.ares.buttonnavigation.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.ares.buttonnavigation.Activity.TeamDetail
import com.example.ares.buttonnavigation.Activity.TeamSearch
import com.example.ares.buttonnavigation.Adapter.TeamAdapter
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
import org.jetbrains.anko.support.v4.ctx

class TeamFragment:Fragment(),AnkoComponent<Context>,MainView{
    lateinit var gson: Gson
    lateinit var apiRepository: ApiRepository
    lateinit var presenter: Presenter
    lateinit var adapter : TeamAdapter
    lateinit var listNamaLiga: ArrayList<String>
    lateinit var listIdLiga: ArrayList<String>
    lateinit var progressBar: ProgressBar
    lateinit var spinnerku: Spinner
    lateinit var rvList: RecyclerView
    lateinit var list: MutableList<Team>
    private var liga = "English Primier League"

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

    override fun showIdLeague(data: List<Leagues>) {
        listIdLiga.clear()
        listNamaLiga.clear()
        for (i in data.indices){

            if (data[i].sport.equals("Soccer")){
                var namaLiga = data[i].league!!
                var idLiga = data[i].idLeague!!
                listNamaLiga.add(namaLiga!!)
                listIdLiga.add(idLiga!!)
            }


        }
        spinnerku.adapter =ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,listNamaLiga)
        spinnerku.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                for(i in listIdLiga.indices){
                    if(listNamaLiga[i] == spinnerku.selectedItem ){
                        liga = listNamaLiga[i]
                    }
                }
                presenter.getTeam(liga)
            }

        }
    }



    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        relativeLayout {
            progressBar = progressBar{

            }.lparams{
                centerHorizontally()
            }
            spinnerku = spinner {
                id = R.id.teamSpinner

            }.lparams(matchParent)
            rvList = recyclerView {
                layoutManager = LinearLayoutManager(activity)
            }.lparams(matchParent) {
                below(R.id.teamSpinner)

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        gson = Gson()
        apiRepository = ApiRepository()
        presenter = Presenter(this,gson,apiRepository)
        listIdLiga = ArrayList()
        listNamaLiga = ArrayList()
        list = mutableListOf()
        presenter.getIdLeague()
        adapter = TeamAdapter(list){

            ctx.startActivity<TeamDetail>("dataTeam" to it)
        }
        rvList.adapter = adapter
        presenter.getTeam(liga)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (inflater != null) inflater.inflate(R.menu.menu_search,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_search ->{
                ctx.startActivity<TeamSearch>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}