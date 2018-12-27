package com.example.ares.buttonnavigation.Activity
import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ares.buttonnavigation.Database.database
import com.example.ares.buttonnavigation.Model.Match
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.NetWorkService.ApiRepository
import com.example.ares.buttonnavigation.NetWorkService.TeamRespon
import com.example.ares.buttonnavigation.NetWorkService.TheSportDBAPI
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import com.example.ares.buttonnavigation.Utils.TanggalIndo
import com.example.ares.buttonnavigation.anko.DetailViewUi
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat

class MatchDetail : AppCompatActivity() {
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
    private var menuItem : Menu? = null
    lateinit var data:Match
    private var isFavorite:Boolean = false

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = DetailViewUi<MatchDetail>().createView(AnkoContext.create(ctx,this))
        setContentView(view)
        supportActionBar?.title = getString(R.string.teamDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



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
        Log.d("Data Parsing","$data")
        favoriteState()

        getImgTeam(data.idHomeTeam,0)
        getImgTeam(data.idAwayTeam,1)
        val dateFormat = SimpleDateFormat("yyyy-dd-MM")
        val date = dateFormat.parse(data.dateEvent)
        val tglIndo     = TanggalIndo().ambilTanggal(date)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      return  when(item?.itemId){
          android.R.id.home -> {
                finish()
              true
            }
            tambahFavorite ->{
                if (isFavorite) hapusFavorite() else tambahFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }}

    private fun getImgTeam(id :String?,imgs:Int){

        doAsync {
        val dataImage = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getNextMatchDetail(id)), TeamRespon::class.java)
           uiThread {
            val imgUrl :List<Team> = ArrayList(dataImage.teams)
               imgUrl[0].teamBadge
               when(imgs){
                   0->{
                       Picasso.get().load( imgUrl[0].teamBadge).into(mImgHome)
                   }
                   1->{
                       Picasso.get().load( imgUrl[0].teamBadge).into(mImgAway)
                   }
               }

           }

        }
    }

  private fun tambahFavorite(){
        try {
            database.use{
                insert(Match.TABEL_FAVORITE,
                        Match.EVENT to data.idEvent,
                        Match.HOME_NAME to data.HomeTeam,
                        Match.AWAY_NAME to data.AwayTeam,
                        Match.Home_Score to data.HomeScore,
                        Match.Away_Score to data.AwayScore,
                        Match.Date_Event to data.dateEvent,

                        Match.Home_Team_id to data.idHomeTeam,
                        Match.Away_Team_id to data.idAwayTeam,

                        Match.Home_Goal_Details to data.HomeGoalDetails,
                        Match.Home_Red_Cards to data.HomeRedCards,
                        Match.Home_Lineup_Goalkeeper to data.HomeLineupGoalkeeper,
                        Match.Home_Lineup_Defense to data.HomeLineupDefense,
                        Match.Home_Lineup_Midfield to data.HomeLineupMidfield,
                        Match.Home_Lineup_Forward to data.HomeLineupForward,
                        Match.Home_Lineup_Substitutes to data.HomeLineupSubstitutes,
                        Match.Home_Formation to data.HomeFormation,

                        Match.Away_Goal_Details to data.AwayGoalDetails,
                        Match.Away_Red_Cards to data.AwayRedCards,
                        Match.Away_Lineup_Goalkeeper to data.AwayLineupGoalkeeper,
                        Match.Away_Lineup_Defense to data.AwayLineupDefense,
                        Match.Away_Lineup_Midfield to data.AwayLineupMidfield,
                        Match.Away_Lineup_Forward to data.AwayLineupForward,
                        Match.Away_Lineup_Substitutes to data.AwayLineupSubstitutes,
                        Match.Away_Formation to data.AwayFormation,

                        Match.Home_Shots to data.HomeShots,
                        Match.Away_Shots to data.AwayShots
                )
        }
            Toast.makeText(ctx,getString(R.string.tambahNotif),Toast.LENGTH_SHORT).show()
        }catch (e: SQLiteConstraintException){
            e.printStackTrace()
        }
    }

  private fun hapusFavorite(){
        try {
            database.use {
                delete(Match.TABEL_FAVORITE,"(Event = {id})","id" to data.idEvent.toString())
            }
            Toast.makeText(ctx,getString(R.string.hapusnotif),Toast.LENGTH_SHORT).show()
        }catch (e:SQLiteConstraintException){
            e.printStackTrace()
        }
    }

 private fun setFavorite(){
        if(isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_add_to_favorites)

    }

   private fun favoriteState(){
        database.use {
            val hasil = select(Match.TABEL_FAVORITE).whereArgs("(Event = {id})","id" to data.idEvent.toString())
            val favorit =hasil.parseList(classParser<Match>())
            if (!favorit.isEmpty()) isFavorite = true
        }
    }
}
