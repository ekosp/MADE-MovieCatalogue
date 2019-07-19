package com.ekosp.dicoding.moviecatalogue.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar

/**
 * Created by Eko.Purnomo on 2019-07-17.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

@Entity(tableName = GlobalVar.TABEL_MOVIE)
data class NewMovie(

        val vote_count: Int,

        @PrimaryKey
        val id: Int,

        val vote_average: Float,
        val title: String?,
        val poster_path: String?,
        val backdrop_path: String?,
        val overview: String?,
        val release_date: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(vote_count)
        parcel.writeInt(id)
        parcel.writeFloat(vote_average)
        parcel.writeString(title)
        parcel.writeString(poster_path)
        parcel.writeString(backdrop_path)
        parcel.writeString(overview)
        parcel.writeString(release_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewMovie> {
        override fun createFromParcel(parcel: Parcel): NewMovie {
            return NewMovie(parcel)
        }

        override fun newArray(size: Int): Array<NewMovie?> {
            return arrayOfNulls(size)
        }
    }
}