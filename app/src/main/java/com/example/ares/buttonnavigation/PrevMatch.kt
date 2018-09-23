package com.example.ares.buttonnavigation

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PrevMatch(

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
        var AwayShots: String? = null


) : Parcelable {
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
                @JvmField
                val CREATOR: Parcelable.Creator<PrevMatch> = object : Parcelable.Creator<PrevMatch> {
                        override fun createFromParcel(source: Parcel): PrevMatch = PrevMatch(source)
                        override fun newArray(size: Int): Array<PrevMatch?> = arrayOfNulls(size)
                }
        }
}