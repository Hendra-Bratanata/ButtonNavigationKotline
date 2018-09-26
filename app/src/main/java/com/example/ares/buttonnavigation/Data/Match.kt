package com.example.ares.buttonnavigation.Data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Match(

        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("strHomeTeam")
        var HomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var AwayTeam: String? = null,

        @SerializedName("intHomeScore")
        var HomeScore: String? = null,

        @SerializedName("intAwayScore")
        var AwayScore: String? = null,

        @SerializedName("strDate")
        var dateEvent: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,

        @SerializedName("strHomeGoalDetails")
        var HomeGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        var HomeRedCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var HomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var HomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var HomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var HomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var HomeLineupSubstitutes: String? = null,

        @SerializedName("strHomeFormation")
        var HomeFormation: String? = null,

        @SerializedName("strAwayGoalDetails")
        var AwayGoalDetails: String? = null,

        @SerializedName("strAwayRedCards")
        var AwayRedCards: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var AwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var AwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var AwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var AwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var AwayLineupSubstitutes: String? = null,

        @SerializedName("strAwayFormation")
        var AwayFormation: String? = null,

        @SerializedName("intHomeShots")
        var HomeShots: String? = null,

        @SerializedName("intAwayShots")
        var AwayShots: String? = null) : Parcelable {

        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(idEvent)
                writeString(HomeTeam)
                writeString(AwayTeam)
                writeString(HomeScore)
                writeString(AwayScore)
                writeString(dateEvent)
                writeString(idHomeTeam)
                writeString(idAwayTeam)
                writeString(HomeGoalDetails)
                writeString(HomeRedCards)
                writeString(HomeLineupGoalkeeper)
                writeString(HomeLineupDefense)
                writeString(HomeLineupMidfield)
                writeString(HomeLineupForward)
                writeString(HomeLineupSubstitutes)
                writeString(HomeFormation)
                writeString(AwayGoalDetails)
                writeString(AwayRedCards)
                writeString(AwayLineupGoalkeeper)
                writeString(AwayLineupDefense)
                writeString(AwayLineupMidfield)
                writeString(AwayLineupForward)
                writeString(AwayLineupSubstitutes)
                writeString(AwayFormation)
                writeString(HomeShots)
                writeString(AwayShots)
        }

        companion object {
                const val TABLE_FAVORITE: String = "TABEL_FAVORITE"

                const val ID: String = "ID_"

                const val EVENT: String = "EVENT_"

                const val DATE_EVENT: String = "DATE_EVENT"

                const val ID_HOME_TEAM: String = "ID_HOME_TEAM"

                const val HOME_TEAM: String = "HOME_TEAM"

                const val HOME_SCORE: String = "HOME_SCORE"

                const val HOME_GOALS: String = "HOME_GOALS"

                const val HOME_CARD: String = "HOME_CARD"

                const val HOME_KEEPER: String = "HOME_KEEPER"

                const val HOME_MINDFIELD: String = "HOME_MINDFIELD"

                const val HOME_SUBTITUTES: String = "HOME_SUBTITUTES"

                const val HOME_FORMATION: String = "HOME_FORMSTION"

                const val HOME_SHOTS: String = "HOME_SHOTS"

                const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"

                const val AWAY_TEAM: String = "AWAY_TEAM"

                const val AWAY_SCORE: String = "AWAY_SCORE"

                const val AWAY_GOALS: String = "AWAY_GOALS"

                const val AWAY_CARD: String = "AWAY_CARD"

                const val AWAY_KEEPER: String = "AWAY_KEEPER"

                const val AWAY_MINDFIELD: String = "AWAY_MINDFIELD"

                const val AWAY_SUBTITUTES: String = "AWAY_SUBTITUTES"

                const val AWAY_FORMATION: String = "AWAY_FORMSTION"

                const val AWAY_SHOTS: String = "AWAY_SHOTS"

                @JvmField
                val CREATOR: Parcelable.Creator<Match> = object : Parcelable.Creator<Match> {
                        override fun createFromParcel(source: Parcel): Match = Match(source)
                        override fun newArray(size: Int): Array<Match?> = arrayOfNulls(size)
                }
        }
}
