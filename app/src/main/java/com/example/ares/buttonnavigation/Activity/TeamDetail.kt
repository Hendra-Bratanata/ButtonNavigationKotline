package com.example.ares.buttonnavigation.Activity

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ares.buttonnavigation.Database.database
import com.example.ares.buttonnavigation.Database.databaseTeam
import com.example.ares.buttonnavigation.Fragment.PlayerFragment
import com.example.ares.buttonnavigation.Fragment.TeamDesFragment
import com.example.ares.buttonnavigation.Model.Team
import com.example.ares.buttonnavigation.R
import com.example.ares.buttonnavigation.R.id.*
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamDetail : AppCompatActivity() {
    private var menuItem : Menu? = null
    lateinit var data:Team
    lateinit var mImgTeamDetail:ImageView
    lateinit var mNamaClub: TextView
    lateinit var mNamaStadion:TextView
    lateinit var mTahunBerdiri:TextView
    private lateinit var fragmentDetailAdapter:MatchPagerAdapter

    lateinit var myViewPager : ViewPager
    lateinit var myTabLayout :TabLayout
    lateinit var teamDetaiFragment: TeamDesFragment
    lateinit var playerFragment: PlayerFragment
    private var isFavorite:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeamDetailUI().setContentView(this)
        val action = supportActionBar
        action!!.setDisplayHomeAsUpEnabled(true)
        action!!.title = "Team Deatail"


        mImgTeamDetail = find(R.id.imgTeamDetail)
        mNamaClub = find(R.id.tvNamaClub)
        mNamaStadion = find(tvNamaStadion)
        mTahunBerdiri = find(tvTahunClub)
        myTabLayout = find(myTabLayoutteamDetail)
        myViewPager = find(teamDetailViewPager)
        data = intent.getParcelableExtra("dataTeam")


        Picasso.get().load(data.teamBadge).into(mImgTeamDetail)
        mNamaClub.text = data.namaTeam
        mTahunBerdiri.text = data.teamFormedYear
        mNamaStadion.text = data.teamStadium
        fragmentDetailAdapter = MatchPagerAdapter(supportFragmentManager)
        teamDetaiFragment = TeamDesFragment()
        playerFragment = PlayerFragment()
        playerFragment.data = data
        teamDetaiFragment.data = data
        fragmentDetailAdapter.addFrag(teamDetaiFragment,"OverView")
        fragmentDetailAdapter.addFrag(playerFragment,"Player")

        myViewPager.adapter = fragmentDetailAdapter
        myTabLayout.setupWithViewPager(myViewPager)
        favoriteState()




    }

    internal inner class MatchPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite,menu)
        menuItem = menu
        setFavorite()
        return true
    }


    private fun hapusFavorite() {
        try {
            databaseTeam.use {
                delete(Team.TABEL_Team,"(idTeam = {id})","id" to data.idTeam.toString())
            }
            Toast.makeText(ctx,"Team Sudah Dihapus dari Favorite",Toast.LENGTH_SHORT).show()
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
    private fun tambahFavorite(){
        try {
            databaseTeam.use {
                insert(Team.TABEL_Team,
                        Team.IDTEAM to data.idTeam,
                        Team.LOVED to data.intLove,
                        Team.Nama_Team to data.namaTeam,
                        Team.Team_Short to data.teamShort,
                        Team.Alternet to data.teamAlternet,
                        Team.Tahun to data.teamFormedYear,
                        Team.Sport to data.teamSport,
                        Team.League to data.teamLeangue,
                        Team.idLeague to data.idLeangue,
                        Team.Devision to data.teamDevision,
                        Team.Manager to data.teamManager,
                        Team.Stadium to data.teamStadium,
                        Team.Keyword to data.teamKeywords,
                        Team.StadiumImg to data.teamStadiumImg,
                        Team.StadiumDes to data.teamStadiumDes,
                        Team.StadiumLoc to data.teamStadiumLocation,
                        Team.StadiumCap to data.teamStadiumCapacity,
                        Team.Website to data.teamWebsite,
                        Team.Facebook to data.teamFacebook,
                        Team.Twitter to data.teamTwitter,
                        Team.Instagram to data.teamInstagram,
                        Team.DesEN to data.teamDescriptionEN,
                        Team.Gender to data.teamGender,
                        Team.Country to data.teamCountry,
                        Team.Badge to data.teamBadge,
                        Team.Jersey to data.teamJersey,
                        Team.Logo to data.teamLogo
                )
            }
            Toast.makeText(ctx,"Team Sudah ditambahkan",Toast.LENGTH_SHORT).show()
        }catch (e: SQLiteConstraintException){
            e.printStackTrace()
        }
    }
    private fun favoriteState(){
        databaseTeam.use {
            val hasil = select(Team.TABEL_Team).whereArgs("(idTeam = {id})","id" to data.idTeam.toString())
            val favorit =hasil.parseList(classParser<Team>())
            if (!favorit.isEmpty()) isFavorite = true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
          android.R.id.home ->{
                finish()
                true
            }
            tambahFavorite -> {
                if (isFavorite) hapusFavorite() else tambahFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
            }
        }


}
    class TeamDetailUI: AnkoComponent<TeamDetail> {
        private lateinit var myviewPager:ViewPager
        override fun createView(ui: AnkoContext<TeamDetail>)= with(ui) {
            relativeLayout {
                    relativeLayout {
                        background = resources.getDrawable(R.color.colorPrimary)
                        id = R.id.lyAtas
                        imageView{
                            id = imgTeamDetail
                        }.lparams(width = 200,height = 250){
                            alignParentTop()
                            centerHorizontally()
                        }
                        textView{
                            textSize = 20f

                            text = context.getString(R.string.namaClub)
                            textColor = Color.WHITE
                            id = R.id.tvNamaClub
                            setTypeface(typeface,Typeface.BOLD)
                        }.lparams{
                            below(imgTeamDetail)
                            centerHorizontally()
                        }
                        textView{
                            textSize = 16f

                            text = context.getString(R.string.namaClub)
                            textColor = Color.WHITE
                            id = R.id.tvTahunClub
                        }.lparams{
                            below(tvNamaClub)
                            centerHorizontally()
                        }

                        textView{
                            textSize = 16f

                            text = context.getString(R.string.namaClub)
                            textColor = Color.WHITE
                            id = R.id.tvNamaStadion

                        }.lparams{
                            below(tvTahunClub)
                            centerHorizontally()
                        }
                    }.lparams(matchParent, wrapContent)


                relativeLayout {
                    coordinatorLayout{
                        lparams(matchParent)
                        appBarLayout {
                            lparams(matchParent, wrapContent)
                            id = R.id.appBarDetailTeam
                     themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark){
                        id =R.id.myTabLayoutteamDetail
                    }.lparams(matchParent){
                                scrollFlags =AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                            }
                        }

                       myviewPager =  viewPager {
                            id =R.id.teamDetailViewPager
                        }.lparams(matchParent)
                        (myviewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()

                    }.lparams(matchParent, matchParent)
                }.lparams(matchParent, matchParent){
                    below(lyAtas)
                }

            }
        }



    }
