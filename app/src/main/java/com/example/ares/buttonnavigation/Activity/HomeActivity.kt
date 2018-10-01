package com.example.ares.buttonnavigation.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ares.buttonnavigation.Fragment.FavoriteFragment
import com.example.ares.buttonnavigation.Fragment.NextFragment
import com.example.ares.buttonnavigation.Fragment.PrevFragment
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*

import kotlinx.android.synthetic.main.activity_home.*
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
               favorites -> {
                   val acttion = supportActionBar
                   acttion?.title = "Teams"
                    loadFavoriteFragment(savedInstanceState)
                }
                prev_match -> {
                    val acttion = supportActionBar
                    acttion?.title = "Match"
                    loadPrevFragment(savedInstanceState)
                }
                next_match ->{
                    val acttion = supportActionBar
                    acttion?.title = "Match"
                    loadNextFragment(savedInstanceState)
                }

                home -> {
                    finish()
                }

            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prev_match
    }

    fun loadPrevFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, PrevFragment(), PrevFragment::class.java.simpleName)
                    .commit()
        }
    }
    fun loadFavoriteFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

    fun loadNextFragment(save:Bundle?) {
        if (save == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, NextFragment(), NextFragment::class.java.simpleName)
                    .commit()
        }
    }

}
