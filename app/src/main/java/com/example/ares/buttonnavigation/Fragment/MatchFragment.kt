package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toolbar
import com.example.ares.buttonnavigation.*
import com.example.ares.buttonnavigation.Activity.DetailActivity
import com.example.ares.buttonnavigation.Adapter.MyPagerAdapter
import com.example.ares.buttonnavigation.Adapter.PrevAdapter
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.Utils.invisible
import com.example.ares.buttonnavigation.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class MatchFragment : Fragment(),AnkoComponent<Context> {


    private lateinit var myViewPager:ViewPager
    lateinit var myTabLayout:TabLayout
    lateinit var toolbar: Toolbar
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            coordinatorLayout {
                lparams(matchParent, matchParent)

                appBarLayout {
                    lparams(matchParent, wrapContent)
                    myTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {


                    }.lparams(matchParent){
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    }
                }
                myViewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
                (myViewPager?.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
            }.lparams(matchParent, matchParent) {

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragmentAdapter = MyPagerAdapter(activity!!.supportFragmentManager)
        myViewPager.adapter = fragmentAdapter
        myTabLayout.setupWithViewPager(myViewPager)

    }

}
