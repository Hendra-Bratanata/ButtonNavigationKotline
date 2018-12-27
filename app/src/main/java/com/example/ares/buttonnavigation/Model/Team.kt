package com.example.ares.buttonnavigation.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Team(
        var id: Long?,

        @SerializedName("idTeam")
        var idTeam: String? = null,
        @SerializedName("intLoved")
        var intLove: String? = null,
        @SerializedName("strTeam")
        var namaTeam: String? = null,
        @SerializedName("strTeamShort")
        var teamShort: String? = null,
        @SerializedName("strAlternate")
        var teamAlternet: String? = null,
        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,
        @SerializedName("strSport")
        var teamSport: String? = null,
        @SerializedName("strLeague")
        var teamLeangue: String? = null,
        @SerializedName("idLeague")
        var idLeangue: String? = null,
        @SerializedName("strDivision")
        var teamDevision: String? = null,
        @SerializedName("strManager")
        var teamManager: String? = null,
        @SerializedName("strStadium")
        var teamStadium: String? = null,
        @SerializedName("strKeywords")
        var teamKeywords: String? = null,
        @SerializedName("strStadiumThumb")
        var teamStadiumImg: String? = null,
        @SerializedName("strStadiumDescription")
        var teamStadiumDes: String? = null,
        @SerializedName("strStadiumLocation")
        var teamStadiumLocation: String? = null,
        @SerializedName("intStadiumCapacity")
        var teamStadiumCapacity: String? = null,
        @SerializedName("strWebsite")
        var teamWebsite: String? = null,
        @SerializedName("strFacebook")
        var teamFacebook: String? = null,
        @SerializedName("strTwitter")
        var teamTwitter: String? = null,
        @SerializedName("strInstagram")
        var teamInstagram: String? = null,
        @SerializedName("strDescriptionEN")
        var teamDescriptionEN: String? = null,
        @SerializedName("strGender")
        var teamGender: String? = null,
        @SerializedName("strCountry")
        var teamCountry: String? = null,
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,
        @SerializedName("strTeamJersey")
        var teamJersey: String? = null,
        @SerializedName("strTeamLogo")
        var teamLogo: String? = null
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
                writeString(idTeam)
                writeString(intLove)
                writeString(namaTeam)
                writeString(teamShort)
                writeString(teamAlternet)
                writeString(teamFormedYear)
                writeString(teamSport)
                writeString(teamLeangue)
                writeString(idLeangue)
                writeString(teamDevision)
                writeString(teamManager)
                writeString(teamStadium)
                writeString(teamKeywords)
                writeString(teamStadiumImg)
                writeString(teamStadiumDes)
                writeString(teamStadiumLocation)
                writeString(teamStadiumCapacity)
                writeString(teamWebsite)
                writeString(teamFacebook)
                writeString(teamTwitter)
                writeString(teamInstagram)
                writeString(teamDescriptionEN)
                writeString(teamGender)
                writeString(teamCountry)
                writeString(teamBadge)
                writeString(teamJersey)
                writeString(teamLogo)
        }

        companion object {
                const val TABEL_Team: String = "Tabel_Team"

                const val ID: String = "Id_"

                const val IDTEAM: String = "idTeam"

                const val LOVED: String = "Love"

                const val Nama_Team: String = "NamaTeam"

                const val Team_Short: String = "Team_Short"

                const val Alternet: String = "teamAlternet"

                const val Tahun: String = "teamFormedYear"

                const val Sport: String = "teamSport"

                const val League: String = "teamLeangue"

                const val idLeague: String = "idLeangue"

                const val Devision: String = "teamDevision"

                const val Manager: String = "teamManager"

                const val Stadium: String = "teamStadium"

                const val Keyword: String = "teamKeywords"

                const val StadiumImg: String = "teamStadiumImg"

                const val StadiumDes: String = "teamStadiumDes"

                const val StadiumLoc: String = "teamStadiumLocation"

                const val StadiumCap: String = "teamStadiumCapacity"

                const val Website: String = "teamWebsite"

                const val Facebook: String = "teamFacebook"

                const val Twitter: String = "teamTwitter"

                const val Instagram: String = "teamInstagram"

                const val DesEN: String = "teamDescriptionEN"

                const val Gender: String = "teamGender"

                const val Country: String = "teamCountry"

                const val Badge: String = "teamBadge"

                const val Jersey: String = "teamJersey"

                const val Logo: String = "teamLogo"

                @JvmField
                val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
                        override fun createFromParcel(source: Parcel): Team = Team(source)
                        override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
                }
        }
}