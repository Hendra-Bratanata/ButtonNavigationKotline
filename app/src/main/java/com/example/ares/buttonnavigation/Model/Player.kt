package com.example.ares.buttonnavigation.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,
        @SerializedName("idTeam")
        var idTeam: String? = null,
        @SerializedName("strNationality")
        var kebangsaan: String? = null,
        @SerializedName("strPlayer")
        var namaPemain: String? = null,
        @SerializedName("strDescriptionEN")
        var deskripsiPemain: String? = null,
        @SerializedName("strCutout")
        var pasPhoto: String? = null,
        @SerializedName("strPosition")
        var Posisi: String? = null,
        @SerializedName("strHeight")
        var tinggi: String? = null,
        @SerializedName("strWeight")
        var berat: String? = null,
        @SerializedName("strThumb")
        var thumb: String? = null,
        @SerializedName("strBanner")
        var banner: String? = null,
        @SerializedName("strFanart1")
        var Fanart1: String? = null,
        @SerializedName("strFanart2")
        var Fanart2: String? = null,
        @SerializedName("strFanart3")
        var Fanart3: String? = null,
        @SerializedName("strFanart4")
        var Fanart4: String? = null

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
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(idPlayer)
                writeString(idTeam)
                writeString(kebangsaan)
                writeString(namaPemain)
                writeString(deskripsiPemain)
                writeString(pasPhoto)
                writeString(Posisi)
                writeString(tinggi)
                writeString(berat)
                writeString(thumb)
                writeString(banner)
                writeString(Fanart1)
                writeString(Fanart2)
                writeString(Fanart3)
                writeString(Fanart4)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Player> = object : Parcelable.Creator<Player> {
                        override fun createFromParcel(source: Parcel): Player = Player(source)
                        override fun newArray(size: Int): Array<Player?> = arrayOfNulls(size)
                }
        }
}