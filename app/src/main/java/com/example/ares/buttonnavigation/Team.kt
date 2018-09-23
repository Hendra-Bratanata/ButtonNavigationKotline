package com.example.ares.buttonnavigation

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Team(

        @SerializedName("strTeam")
        var namaTeam: String? = null,

        @SerializedName("strTeamBadge")
        var logo: String? = null

//        @SerializedName("strAwayTeam")
//        var AwayTeam :String? = null,
//
//        @SerializedName("intHomeScore")
//        var HomeScore :String? = null,
//
//        @SerializedName("intAwayScore")
//        var AwayScore :String? = null,
//
//        @SerializedName("strDate")
//        var dateEvent :String? = null,
//
//        @SerializedName("idHomeTeam")
//        var idHomeTeam :String? = null,
//
//        @SerializedName("idAwayTeam")
//        var idAwayTeam :String? = null
) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(namaTeam)
                writeString(logo)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
                        override fun createFromParcel(source: Parcel): Team = Team(source)
                        override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
                }
        }
}