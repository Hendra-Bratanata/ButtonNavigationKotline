package com.example.ares.buttonnavigation.Activity

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ares.buttonnavigation.Model.Player
import com.example.ares.buttonnavigation.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailPlayer: AppCompatActivity(){
    lateinit var imgBanner: ImageView
    lateinit var tvBerat: TextView
    lateinit var tvPlayerDec: TextView
    lateinit var tvTinggi: TextView
    lateinit var tvPosisi: TextView
    lateinit var data:Player
    lateinit var dataImg:String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
       requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        PlayerUI().setContentView(this)
        imgBanner = find(R.id.imgBannerPlayer)
        tvBerat = find(R.id.berat)
        tvTinggi = find(R.id.tinggi)
        tvPosisi = find(R.id.posisi)
        tvPlayerDec = find(R.id.playerDes)
        data = intent.getParcelableExtra("dataPlayer")
        dataImg = data.Fanart1.toString()
        if (data.Fanart1 == null){
             dataImg = data.thumb.toString()
            if (data.thumb == null){
                dataImg = data.Fanart2.toString()
                if (data.Fanart2 == null){
                    dataImg = data.Fanart3.toString()
                    if (data.Fanart3 == null){
                        dataImg = data.Fanart4.toString()
                    }
                }
            }

        }

        Picasso.get().load(dataImg).into(imgBanner)
        tvBerat.text = data.berat
        tvTinggi.text = data.tinggi
        tvPosisi.text = data.Posisi
        tvPlayerDec.text = data.deskripsiPemain
    }
}
class PlayerUI: AnkoComponent<DetailPlayer> {
    override fun createView(ui: AnkoContext<DetailPlayer>) = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                linearLayout {
                    imageView {
                        id = R.id.imgBannerPlayer
                        scaleType = ImageView.ScaleType.CENTER_INSIDE
                    }.lparams(matchParent, matchParent)
                }.lparams(matchParent,400){
                    bottomMargin = dip(15)
                }

                relativeLayout {
                    relativeLayout {
                        textView{
                            id = R.id.tvBerat
                            textSize = 20f
                            text = context.getString(R.string.weights)
                            textColor = Color.BLUE
                        }.lparams(wrapContent){
                            centerHorizontally()
                            marginStart = dip(20)
                        }
                        textView{
                            id = R.id.berat
                            textSize = 40f
                            text = "89.67"
                            textColor = Color.BLUE
                        }.lparams(wrapContent){
                            centerHorizontally()
                            marginStart = dip(20)
                            below(R.id.tvBerat)
                        }
                    }.lparams(wrapContent){
                        alignParentLeft()
                        marginStart = dip(20)
                        topMargin = dip(10)
                        bottomMargin = dip(30)
                    }
                    relativeLayout {
                        textView{
                            id = R.id.tvBerat
                            textSize = 20f
                            text = context.getString(R.string.height)
                            textColor = Color.BLUE
                        }.lparams(wrapContent){
                            centerHorizontally()
                            marginStart = dip(20)
                        }
                        textView{
                            id = R.id.tinggi
                            textSize = 40f
                            text = "1.98"
                            textColor = Color.BLUE
                        }.lparams(wrapContent){
                            centerHorizontally()
                            marginStart = dip(20)
                            below(R.id.tvBerat)
                        }
                    }.lparams(wrapContent){
                        alignParentRight()
                        topMargin = dip(10)
                        marginEnd = dip(20)
                        bottomMargin = dip(30)
                    }
                    textView{
                        id = R.id.posisi
                        textSize = 16f
                        textColor = Color.BLUE

                    }.lparams(wrapContent, wrapContent){
                        centerHorizontally()
                        alignParentBottom()

                    }

                }.lparams(matchParent, wrapContent)


                view{
                    id = R.id.viewGaris
                    backgroundColor = Color.GRAY
                }.lparams(matchParent,dip(2))
                scrollView {
                    textView{
                        id = R.id.playerDes
                    }.lparams{
                        margin = dip(16)
                    }
                }



            }
        }
    }
}


