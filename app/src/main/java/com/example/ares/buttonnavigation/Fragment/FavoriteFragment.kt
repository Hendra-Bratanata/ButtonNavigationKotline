package com.example.ares.buttonnavigation.Fragment


import android.os.Bundle
import android.support.v4.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ares.buttonnavigation.R

class FavoriteFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_favorite,container,false)

        return view
    }
}