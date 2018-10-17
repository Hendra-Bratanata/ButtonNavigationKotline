package com.example.ares.buttonnavigation.anko

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.example.ares.buttonnavigation.R
import org.jetbrains.anko.*

class TeamUi<T>:AnkoComponent<T>{
    override fun createView(ui: AnkoContext<T>): View {
        return with(ui){
            linearLayout {
                orientation = LinearLayout.VERTICAL
              linearLayout{
                  orientation = LinearLayout.HORIZONTAL
                  padding = dip(5)

                  imageView{
                      id = R.id.imgTeam
                  }.lparams(width = dip(80),height = dip(60))
                  textView{
                      id =R.id.tv_namaTeam
                      textSize = 20f
                      textColor = Color.BLACK
                  }.lparams(matchParent, wrapContent){
                      topMargin = dip(15)
                  }

              }.lparams(matchParent, wrapContent){
                  margin = dip(10)
              }

             }
        }
    }

}