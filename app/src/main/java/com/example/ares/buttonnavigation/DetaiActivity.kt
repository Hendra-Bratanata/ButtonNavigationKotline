package com.example.ares.buttonnavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import anko.DetailViewUi
import com.example.ares.buttonnavigation.R.id.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetaiActivity : AppCompatActivity() {
    lateinit var gson:Gson
    lateinit var apiRepository: ApiRepository
    lateinit var mImgAway: ImageView
    lateinit var mImgHome: ImageView
    lateinit var mTitleHome:TextView
    lateinit var mTitleAway:TextView
    lateinit var mFormationHome:TextView
    lateinit var mFormationAway:TextView
    lateinit var mHomeScore:TextView
    lateinit var mAwayScore:TextView
    lateinit var mTvTanggal:TextView
    lateinit var mGoalsAway :TextView
    lateinit var mGoalsHome :TextView
    lateinit var mShotsHome:TextView
    lateinit var mShotsAway:TextView
    lateinit var mHomeKeeper:TextView
    lateinit var mAwayKeeper:TextView
    lateinit var mHomeDefense:TextView
    lateinit var mAwayDefense:TextView
    lateinit var mHomeMindField:TextView
    lateinit var mAwayMindField:TextView
    lateinit var mHomeForward:TextView
    lateinit var mAwayForward:TextView
    lateinit var mHomeSubtitus:TextView
    lateinit var mAwaySubtitus:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = DetailViewUi<DetaiActivity>().createView(AnkoContext.create(ctx,this))
        setContentView(view)

        mImgHome    = view.find(imgHome)
        mImgAway    = view.find(imgAway)
        mHomeScore  = view.find(tvHomeScoreDetail)
        mTitleHome  = view.find(homeTitle)
        mTitleAway  = view.find(awayTitle)
        mFormationHome  = view.find(homeFormation)
        mFormationAway  = view.find(awayFormation)
        mShotsHome  = view.find(homeShots)
        mShotsAway  = view.find(awayShots)
        mAwayKeeper = view.find(tvAwayKeeper)
        mHomeKeeper = view.find(tvHomeKeeper)
        mAwayDefense = view.find(tvAwayDefense)
        mHomeDefense = view.find(tvHomeDefense)
        mHomeMindField = view.find(tvHomeMindField)
        mAwayMindField  = view.find(tvAwayMindField)
        mAwayForward  = view.find(tvAwayForward)
        mHomeForward= view.find(tvHomeForward)
        mHomeSubtitus  = view.find(tvHomeSubstitutes)
        mAwaySubtitus  = view.find(tvAwaySubstitutes)
        mAwayScore  = view.find(AwayScoredetail)
        mTvTanggal   = view.find(tvTanggal)
        mGoalsHome      =view.find(R.id.tvGoalsHome)
        mGoalsAway      =view.find(R.id.tvGoalsAway)
        gson = Gson()
        apiRepository = ApiRepository()


        val data:PrevMatch = intent.getParcelableExtra("data")




        getImgTeam(data.idHomeTeam,0)
        getImgTeam(data.idAwayTeam,1)
        val tglIndo     = TanggalIndo().ambilTanggal(data.dateEvent)
        mShotsHome.text = data.HomeShots
        mShotsAway.text = data.AwayShots
        mTitleHome.text = data.HomeTeam
        mTitleAway.text = data.AwayTeam
        mFormationHome.text = data.HomeFormation
        mFormationAway.text = data.AwayFormation
        mGoalsHome.text = data.HomeGoalDetails?.replace(";","\n")
        mGoalsAway.text = data.AwayGoalDetails?.replace(";","\n")
        mHomeScore.text = data.HomeScore
        mAwayScore.text = data.AwayScore
        mTvTanggal.text = tglIndo
        mHomeKeeper.text = data.HomeLineupGoalkeeper
        mAwayKeeper.text = data.AwayLineupGoalkeeper
        mHomeDefense.text= data.HomeLineupDefense?.replace("; ","\n")
        mAwayDefense.text= data.AwayLineupDefense?.replace("; ","\n")
        mHomeMindField.text= data.HomeLineupMidfield?.replace("; ","\n")
        mAwayDefense.text= data.AwayLineupMidfield?.replace("; ","\n")
        mHomeForward.text= data.HomeLineupForward?.replace("; ","\n")
        mAwayForward.text= data.AwayLineupForward?.replace("; ","\n")
        mHomeSubtitus.text= data.HomeLineupSubstitutes?.replace("; ","\n")
        mAwaySubtitus.text= data.AwayLineupSubstitutes?.replace("; ","\n")



    }
    fun getImgTeam(id :String?,imgs:Int){

        doAsync {
        val dataImage = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatchDetail(id)),TeamRespon::class.java)
           uiThread {
            Log.d("asyc",dataImage.teams.toString())
            val imgUrl :List<Team> = ArrayList(dataImage.teams)
               imgUrl.get(0).logo
               when(imgs){
                   0->{
                       Picasso.get().load( imgUrl.get(0).logo).into(mImgHome)
                       Log.d("imgUr1", imgUrl.get(0).logo)
                   }
                   1->{
                       Picasso.get().load( imgUrl.get(0).logo).into(mImgAway)
                       Log.d("imgUr2", imgUrl.get(0).logo)
                   }
               }

           }

        }
    }
}
