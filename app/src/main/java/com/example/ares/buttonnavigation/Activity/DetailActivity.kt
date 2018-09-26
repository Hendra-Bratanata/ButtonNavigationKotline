package com.example.ares.buttonnavigation.Activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.ares.buttonnavigation.ankoUI.DetailViewUi
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.Data.Team
import com.example.ares.buttonnavigation.Data.TeamRespon
import com.example.ares.buttonnavigation.NetworkService.ApiRepository
import com.example.ares.buttonnavigation.NetworkService.TheSportDBAPI
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.Utils.database
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar
import java.sql.SQLClientInfoException

class DetailActivity : AppCompatActivity() {
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
     private var menuItem : Menu? =null
    private var isFavorite: Boolean = false
    lateinit var id: String
    lateinit var view: View
    lateinit var data:Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         view = DetailViewUi<DetailActivity>().createView(AnkoContext.create(ctx,this))
        setContentView(view)
        favoriteState()
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


        data = intent.getParcelableExtra("data")




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
        mAwayMindField.text= data.AwayLineupMidfield?.replace("; ","\n")
        mHomeForward.text= data.HomeLineupForward?.replace("; ","\n")
        mAwayForward.text= data.AwayLineupForward?.replace("; ","\n")
        mHomeSubtitus.text= data.HomeLineupSubstitutes?.replace("; ","\n")
        mAwaySubtitus.text= data.AwayLineupSubstitutes?.replace("; ","\n")



    }
    fun getImgTeam(id :String?,imgs:Int){

        doAsync {
        val dataImage = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatchDetail(id)), TeamRespon::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        menuItem = menu
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            add_to_favorite ->{
                if (isFavorite)
                    hapusFavorite()
                else
                tambahFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon =  ContextCompat.getDrawable(this,R.drawable.ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_add_to_favorite)
    }


    private fun hapusFavorite(){
        try {
            database.use {
                delete(Match.TABLE_FAVORITE,"(EVENT = {id})", "id" to id)
            }
            snackbar(view,"Telah dihapus dari favorite").show()
        }catch (e:SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
    }
    private fun favoriteState(){
        database.use {
            val hasil = select(Match.TABLE_FAVORITE).whereArgs("(EVENT = {id})","id" to id)
            val favorite = hasil.parseList(classParser<Match>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    private fun tambahFavorite(){

        try {
            database.use {
                insert(Match.TABLE_FAVORITE,
                        Match.EVENT to data.idEvent,
                        Match.DATE_EVENT to data.dateEvent,
                        Match.ID_HOME_TEAM to data.idHomeTeam,
                        Match.HOME_TEAM to data.HomeTeam,
                        Match.HOME_SCORE to data.HomeScore,
                        Match.HOME_GOALS to data.HomeGoalDetails,
                        Match.HOME_CARD to data.HomeRedCards,
                        Match.HOME_KEEPER to data.HomeLineupGoalkeeper,
                        Match.HOME_MINDFIELD to data.HomeLineupMidfield,
                        Match.HOME_SUBTITUTES to data.HomeLineupSubstitutes,
                        Match.HOME_FORMATION to data.HomeLineupSubstitutes,
                        Match.HOME_SHOTS to data.HomeShots,
                        Match.ID_AWAY_TEAM to data.idAwayTeam,
                        Match.AWAY_TEAM to data.AwayTeam,
                        Match.AWAY_SCORE to data.AwayScore,
                        Match.AWAY_GOALS to data.AwayGoalDetails,
                        Match.AWAY_CARD to data.AwayRedCards,
                        Match.AWAY_KEEPER to data.HomeLineupGoalkeeper,
                        Match.AWAY_MINDFIELD to data.AwayLineupMidfield,
                        Match.AWAY_SUBTITUTES to data.AwayLineupSubstitutes,
                        Match.AWAY_FORMATION to data.AwayFormation,
                        Match.AWAY_SHOTS to data.AwayShots)
            }
        }catch (e: SQLiteConstraintException){

        }
    }
}
