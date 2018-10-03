package com.example.ares.buttonnavigation.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ares.buttonnavigation.Model.Match
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context ): ManagedSQLiteOpenHelper(ctx,"FavoriteMatch.db",null,1) {

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
        db?.createTable(Match.TABEL_FAVORITE, true,
                Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Match.EVENT to TEXT + UNIQUE,
                Match.HOME_NAME to TEXT,
                Match.AWAY_NAME to TEXT,
                Match.Home_Score to TEXT,
                Match.Away_Score to TEXT,
                Match.Date_Event to TEXT,

                Match.Home_Team_id to TEXT,
                Match.Away_Team_id to TEXT,

                Match.Home_Goal_Details to TEXT,
                Match.Home_Red_Cards to TEXT,
                Match.Home_Lineup_Goalkeeper to TEXT,
                Match.Home_Lineup_Defense to TEXT,
                Match.Home_Lineup_Midfield to TEXT,
                Match.Home_Lineup_Forward to TEXT,
                Match.Home_Lineup_Substitutes to TEXT,
                Match.Home_Formation to TEXT,

                Match.Away_Goal_Details to TEXT,
                Match.Away_Red_Cards to TEXT,
                Match.Away_Lineup_Goalkeeper to TEXT,
                Match.Away_Lineup_Defense to TEXT,
                Match.Away_Lineup_Midfield to TEXT,
                Match.Away_Lineup_Forward to TEXT,
                Match.Away_Lineup_Substitutes to TEXT,
                Match.Away_Formation to TEXT,

                Match.Home_Shots to TEXT,
                Match.Away_Shots to TEXT
                )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABEL_FAVORITE, true)
    }
}


    val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
