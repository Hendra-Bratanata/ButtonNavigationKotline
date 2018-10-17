package com.example.ares.buttonnavigation.Fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class FavoriteViewTabFragment : Fragment(),AnkoComponent<Context>{

    private lateinit var favoriteFragmentAdapter: FavoritePagerAdapter
    lateinit var myTabLayout : TabLayout
    lateinit var favoriteViewPage: ViewPager
    lateinit var favoriteFragment: FavoriteMatchFragment
    lateinit var teamFavorite:FavoriteTeamFragment
    lateinit var data:Team

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {
            coordinatorLayout {
                appBarLayout {
                    id = R.id.appBarFavorite
                   myTabLayout = themedTabLayout (R.style.ThemeOverlay_AppCompat_Dark){
                        id =R.id.myTabLayoutFavorite
                    }.lparams(matchParent){
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    }
                }.lparams(matchParent, wrapContent)
                favoriteViewPage = viewPager{
                    id = R.id.favoriteViewPager
                }.lparams(matchParent)
                (favoriteViewPage.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
            }.lparams(matchParent)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteFragment = FavoriteMatchFragment()
        teamFavorite = FavoriteTeamFragment()
        favoriteFragmentAdapter = FavoritePagerAdapter(childFragmentManager)
        favoriteFragmentAdapter.addFrag(favoriteFragment,"Matchs")

        favoriteFragmentAdapter.addFrag(teamFavorite,"Team")
        favoriteViewPage.adapter = favoriteFragmentAdapter
        myTabLayout.setupWithViewPager(favoriteViewPage)
    }





    internal inner class FavoritePagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
