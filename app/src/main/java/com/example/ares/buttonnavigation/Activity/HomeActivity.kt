package com.example.ares.buttonnavigation.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.ares.buttonnavigation.Fragment.FavoriteViewTabFragment
import com.example.ares.buttonnavigation.Fragment.MatchViewTabFragment
import com.example.ares.buttonnavigation.Fragment.TeamFragment
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.anko.HomeActivityUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find


class HomeActivity : AppCompatActivity() {

    private lateinit var mBottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = HomeActivityUI<HomeActivity>().createView(AnkoContext.create(ctx,this))
        setContentView(view)
        mBottomNavigation = view.find(R.id.bottom_navigation)
      mBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
               favorites -> {
                   val actionBar = supportActionBar
                   actionBar?.title = "Favorite"
                    loadFavoriteFragment(savedInstanceState)
                }
                prev_match -> {
                    val actionBar = supportActionBar
                    actionBar?.title = "Match"
                    loadPrevFragment(savedInstanceState)

                }
                next_match ->{
                    val actionBar = supportActionBar
                    actionBar?.title = "Teams"
                    loadTeamFragment(savedInstanceState)
                }

                home -> {
                    finish()
                }

            }
            true
        }
       mBottomNavigation.selectedItemId = R.id.prev_match
    }


   private fun loadFavoriteFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FavoriteViewTabFragment(), FavoriteViewTabFragment::class.java.simpleName)
                    .commit()
        }
    }

  private  fun loadTeamFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadPrevFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container,MatchViewTabFragment(),MatchViewTabFragment::class.java.simpleName)
                    .commit()
        }
    }
}
