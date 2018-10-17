package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.ares.buttonnavigation.Activity.MatchDetail
import com.example.ares.buttonnavigation.Adapter.MatchAdapter
import com.example.ares.buttonnavigation.Model.Leagues
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class NextFragment : Fragment(),AnkoComponent<Context>, MainView {
    override fun showTeam(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        spinnerku.adapter = ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,listNamaLiga)

        spinnerku.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                for (i in listIdLiga.indices){
                    if (listNamaLiga[i] == spinnerku.selectedItem){
                        liga = listIdLiga[i]
                    }
                }
                presenter.getNextMatch(liga)
            }


        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Match>) {
        list.clear()
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }
    private lateinit var listNamaLiga: ArrayList<String>
    private lateinit var listIdLiga: ArrayList<String>
    private var liga = "4328"
    private lateinit var spinnerku: Spinner
    private lateinit var list: MutableList<Match>
    private lateinit var adapter: MatchAdapter
    private lateinit var gson: Gson
    private lateinit var apiRepository: ApiRepository
    private lateinit var rvList:RecyclerView
    private lateinit var tglIndo : TanggalIndo
    private lateinit var presenter: Presenter
    private lateinit var progressBar: ProgressBar


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            progressBar = progressBar{

            }.lparams{
                centerHorizontally()
            }
            spinnerku = spinner {
                id = R.id.spinKuNext

            }.lparams(matchParent)
            rvList = recyclerView {
                id = R.id.rv_list_Next
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height =
            wrapContent) {
                above(R.id.navigation)
                below(R.id.spinKuNext)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list = mutableListOf()
        apiRepository = ApiRepository()
        tglIndo = TanggalIndo()
        listNamaLiga = ArrayList()
        listIdLiga = ArrayList()
        gson = Gson()
        presenter = Presenter(this, gson, apiRepository)
        presenter.getIdLeague()
        adapter = MatchAdapter(list) {

            ctx.startActivity<MatchDetail>("data" to it)
        }


        rvList.adapter = adapter
        presenter.getNextMatch(liga)


    }

}
