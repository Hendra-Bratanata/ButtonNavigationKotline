package com.example.ares.buttonnavigation.Fragment


import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import com.example.ares.buttonnavigation.Activity.SearchActivity
import com.example.ares.buttonnavigation.Adapter.MyPagerAdapter
import com.example.ares.buttonnavigation.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class MatchFragment : Fragment(),AnkoComponent<Context>{



    private lateinit var myViewPager:ViewPager
    private lateinit var myTabLayout:TabLayout
    lateinit var fragmentAdapter: MyPagerAdapter

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            coordinatorLayout {
                lparams(matchParent, matchParent)

                appBarLayout {
                    lparams(matchParent, wrapContent)
                    id = R.id.app
                    myTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    }.lparams(matchParent){
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    }
                }
                myViewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
                (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
            }.lparams(matchParent, matchParent) {


            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        fragmentAdapter = MyPagerAdapter(childFragmentManager)
        myViewPager.adapter = fragmentAdapter
        myTabLayout.setupWithViewPager(myViewPager)






    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (inflater != null) inflater.inflate(R.menu.menu_search,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_search ->{
            ctx.startActivity<SearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
