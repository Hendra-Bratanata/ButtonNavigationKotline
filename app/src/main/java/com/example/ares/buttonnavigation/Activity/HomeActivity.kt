package com.example.ares.buttonnavigation.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.ares.buttonnavigation.Fragment.FavoriteFragment
import com.example.ares.buttonnavigation.Fragment.MatchFragment
import com.example.ares.buttonnavigation.Fragment.NextFragment
import com.example.ares.buttonnavigation.Fragment.PrevFragment
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.anko.HomeActivityUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find


class HomeActivity : AppCompatActivity() {

    lateinit var mBottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = HomeActivityUI<HomeActivity>().createView(AnkoContext.create(ctx,this))
        setContentView(view)
        mBottomNavigation = view.find(R.id.bottom_navigation)
      mBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
               favorites -> {
                   val actionBar = supportActionBar
                   actionBar?.title = "Teams"
                    loadFavoriteFragment(savedInstanceState)
                }
                prev_match -> {
                    val actionBar = supportActionBar
                    actionBar?.title = "Match"
                    loadPrevFragment(savedInstanceState)
                }
                next_match ->{
                    val actionBar = supportActionBar
                    actionBar?.title = "Next Match"
                    loadNextFragment(savedInstanceState)
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
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

  private  fun loadNextFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, NextFragment(), NextFragment::class.java.simpleName)
                    .commit()
        }
    }
    fun loadPrevFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}
