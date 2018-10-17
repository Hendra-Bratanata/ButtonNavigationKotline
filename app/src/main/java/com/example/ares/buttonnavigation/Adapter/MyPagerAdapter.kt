package com.example.ares.buttonnavigation.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.ares.buttonnavigation.Fragment.NextFragment
import com.example.ares.buttonnavigation.Fragment.PrevFragment

class MyPagerAdapter(manager:FragmentManager):FragmentPagerAdapter(manager){


    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0-> {
                Log.d("FragmentAdaptrer","Prev")
                    PrevFragment()
            }
            else->{
                Log.d("FragmentAdapter","Next")
                    NextFragment()

            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->{
                "PREV MATCH"
            }
            else->{

                return "NEXT MATCH"
            }
        }
    }

}