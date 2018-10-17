package com.example.ares.buttonnavigation.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

class TeamDesFragment:Fragment(),AnkoComponent<Context>{
 lateinit var data :Team
    lateinit var mTvDeskirpsi:TextView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            scrollView {
                textView{
                    id = R.id.deskripsi

                }.lparams(matchParent, wrapContent){
                    margin = 20
                    padding = 5
                }

            }
         lparams(matchParent)


        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTvDeskirpsi = find(R.id.deskripsi)
        mTvDeskirpsi.text = data.teamDescriptionEN

    }

}