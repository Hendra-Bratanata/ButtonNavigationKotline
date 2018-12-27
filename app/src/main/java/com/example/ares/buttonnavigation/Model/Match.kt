package com.example.ares.buttonnavigation.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Match(
        val id: Long?,

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

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strSport")
        var sport: String? = null,

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
        var AwayShots: String? = null


) : Parcelable {
        constructor(source: Parcel) : this(
                source.readValue(Long::class.java.classLoader) as Long?,
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
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeValue(id)
                writeString(idEvent)
                writeString(HomeTeam)
                writeString(AwayTeam)
                writeString(HomeScore)
                writeString(AwayScore)
                writeString(dateEvent)
                writeString(sport)
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
                const val TABEL_FAVORITE: String = "Tabel_Favorite"

                const val ID: String = "Id_"

                const val EVENT: String = "Event"

                const val HOME_NAME: String = "HomeName"

                const val AWAY_NAME: String = "AwayName"

                const val Home_Score: String = "HomeScore"

                const val Away_Score: String = "AwayScore"

                const val Date_Event: String = "DateEvent"

                const val Sport_Event: String = "SportEvent"

                const val Home_Team_id: String = "HomeTeamID"

                const val Away_Team_id: String = "AwayTeamID"

                const val Home_Goal_Details: String = "GoalDetails"

                const val Home_Red_Cards: String = "HomeCard"

                const val Home_Lineup_Goalkeeper: String = "HomeKeeper"

                const val Home_Lineup_Defense: String = "HomeDefense"

                const val Home_Lineup_Midfield: String = "HomeMindfield"

                const val Home_Lineup_Forward: String = "HomeForward"

                const val Home_Lineup_Substitutes: String = "HomeSubstitutes"

                const val Home_Formation: String = "HomeFormation"

                const val Away_Goal_Details: String = "AwayGoals"

                const val Away_Red_Cards: String = "AwayCard"

                const val Away_Lineup_Goalkeeper: String = "AwayKeeper"

                const val Away_Lineup_Defense: String = "AwayDefense"

                const val Away_Lineup_Midfield: String = "AwayMindfield"

                const val Away_Lineup_Forward: String = "AwayForward"

                const val Away_Lineup_Substitutes: String = "AwaySubstitutes"

                const val Away_Formation: String = "AwayFormation"

                const val Home_Shots: String = "HomeShot"

                const val Away_Shots: String = "AwayShot"

                @JvmField
                val CREATOR: Parcelable.Creator<Match> = object : Parcelable.Creator<Match> {
                        override fun createFromParcel(source: Parcel): Match = Match(source)
                        override fun newArray(size: Int): Array<Match?> = arrayOfNulls(size)
                }
        }
}