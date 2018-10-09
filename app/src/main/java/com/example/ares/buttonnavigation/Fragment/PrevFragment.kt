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
import android.widget.Toast
import com.example.ares.buttonnavigation.*
import com.example.ares.buttonnavigation.Activity.DetailActivity
import com.example.ares.buttonnavigation.Adapter.PrevAdapter
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.anko.Presenter
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import com.example.ares.buttonnavigation.anko.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class PrevFragment : Fragment(),AnkoComponent<Context>,MainView {


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
    private lateinit var presenter: Presenter
    private lateinit var list: MutableList<Match>
    private lateinit var adapter: PrevAdapter
    private lateinit var gson: Gson
    private lateinit var apiRepository: ApiRepository
    private lateinit var rvList:RecyclerView
    private lateinit var tglIndo : TanggalIndo
    private lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            progressBar = progressBar{

            }.lparams{
                centerHorizontally()
            }
            rvList = recyclerView {
                id = R.id.rv_list
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height =
            matchParent) {
                above(R.id.navigation)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tglIndo = TanggalIndo()
        list = mutableListOf()
        adapter = PrevAdapter(list) {
            val toast = Toast.makeText(ctx, it.idEvent, Toast.LENGTH_SHORT)
            toast.show()
            ctx.startActivity<DetailActivity>("data" to it)
        }
        gson = Gson()
        apiRepository = ApiRepository()
        rvList.adapter = adapter
        presenter = Presenter(this, gson, apiRepository)
        presenter.getPrevMatch()
    }

}
