package com.example.ares.buttonnavigation.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ares.buttonnavigation.Model.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelperTeam(ctx: Context ): ManagedSQLiteOpenHelper(ctx,"FavoriteTeam.db",null,1) {

    companion object {
        private var instance: MyDatabaseOpenHelperTeam? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelperTeam {
            if (instance == null) {
                instance = MyDatabaseOpenHelperTeam(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelperTeam

        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Team.TABEL_Team, true,
                Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Team.IDTEAM to TEXT + UNIQUE,
                Team.LOVED to TEXT,
                Team.Nama_Team to TEXT,
                Team.Team_Short to TEXT,
                Team.Alternet to TEXT,
                Team.Tahun to TEXT,
                Team.Sport to TEXT,

                Team.League to TEXT,
                Team.idLeague to TEXT,

                Team.Devision to TEXT,
                Team.Manager to TEXT,
                Team.Stadium to TEXT,
                Team.Keyword to TEXT,
                Team.StadiumImg to TEXT,
                Team.StadiumDes to TEXT,
                Team.StadiumLoc to TEXT,
                Team.StadiumCap to TEXT,

                Team.Website to TEXT,
                Team.Facebook to TEXT,
                Team.Twitter to TEXT,
                Team.Instagram to TEXT,

                Team.DesEN to TEXT,
                Team.Gender to TEXT,
                Team.Country to TEXT,
                Team.Badge to TEXT,

                Team.Jersey to TEXT,
                Team.Logo to TEXT
                )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Team.TABEL_Team, true)
    }
}


    val Context.databaseTeam: MyDatabaseOpenHelperTeam
    get() = MyDatabaseOpenHelperTeam.getInstance(applicationContext)
