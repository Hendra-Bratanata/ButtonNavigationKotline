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
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class NextFragment : Fragment(),AnkoComponent<Context>, MainView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMatchDetail(data: List<Match>) {
        list.clear()
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private lateinit var list: MutableList<Match>
    private lateinit var adapter: PrevAdapter
    private lateinit var gson: Gson
    private lateinit var apiRepository: ApiRepository
    private lateinit var rvList:RecyclerView
    private lateinit var tglIndo : TanggalIndo
    private lateinit var presenter: Presenter
    private lateinit var progressBar: ProgressBar


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            rvList = recyclerView {
                id = R.id.rv_list_Next
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
        list = mutableListOf()
        adapter = PrevAdapter(list) {
            val toast = Toast.makeText(ctx, it.idEvent, Toast.LENGTH_SHORT)
            toast.show()
            tglIndo = TanggalIndo()
            ctx.startActivity<DetailActivity>("data" to it)
        }
        gson = Gson()
        apiRepository = ApiRepository()
        tglIndo = TanggalIndo()
        presenter = Presenter(this, gson, apiRepository)
        rvList.adapter = adapter
        presenter.getNextMatch()

    }

}
