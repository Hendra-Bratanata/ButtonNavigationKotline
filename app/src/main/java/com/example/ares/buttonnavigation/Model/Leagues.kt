package com.example.ares.buttonnavigation.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Leagues(
        @SerializedName("idLeague")
        var idLeague: String? = null,
        @SerializedName("strLeague")
        var league: String? = null,
        @SerializedName("strSport")
        var sport: String? = null

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(idLeague)
        writeString(league)
        writeString(sport)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Leagues> = object : Parcelable.Creator<Leagues> {
            override fun createFromParcel(source: Parcel): Leagues = Leagues(source)
            override fun newArray(size: Int): Array<Leagues?> = arrayOfNulls(size)
        }
    }
}