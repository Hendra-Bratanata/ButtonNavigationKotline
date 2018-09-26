package com.example.ares.buttonnavigation.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ares.buttonnavigation.Data.Match
import com.example.ares.buttonnavigation.Data.Team
import org.jetbrains.anko.db.*
import java.awt.font.TextAttribute

class MyDatabaseOpenHelper(ctx:Context) :
        ManagedSQLiteOpenHelper(ctx,"FavoriteMatch.db",null,1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper

        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Match.TABLE_FAVORITE, true,
                Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Match.EVENT to TEXT + UNIQUE,
                Match.DATE_EVENT to TEXT,
                Match.ID_HOME_TEAM to TEXT,
                Match.HOME_TEAM to TEXT,
                Match.HOME_SCORE to TEXT,
                Match.HOME_GOALS to TEXT,
                Match.HOME_CARD to TEXT,
                Match.HOME_KEEPER to TEXT,
                Match.HOME_MINDFIELD to TEXT,
                Match.HOME_SUBTITUTES to TEXT,
                Match.HOME_FORMATION to TEXT,
                Match.HOME_SHOTS to TEXT,
                Match.ID_AWAY_TEAM to TEXT,
                Match.AWAY_TEAM to TEXT,
                Match.AWAY_SCORE to TEXT,
                Match.AWAY_GOALS to TEXT,
                Match.AWAY_CARD to TEXT,
                Match.AWAY_KEEPER to TEXT,
                Match.AWAY_MINDFIELD to TEXT,
                Match.AWAY_SUBTITUTES to TEXT,
                Match.AWAY_FORMATION to TEXT,
                Match.AWAY_SHOTS to TEXT


        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
