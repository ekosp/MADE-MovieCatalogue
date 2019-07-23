package com.ekosp.dicoding.moviecatalogue.model

import android.database.Cursor
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
data class Movie(

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

    constructor(cursor: Cursor) :this(
        cursor.getInt(cursor.getColumnIndexOrThrow("vote_count")),
        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
        cursor.getFloat(cursor.getColumnIndexOrThrow("vote_average")),
        cursor.getString(cursor.getColumnIndexOrThrow("title")),
        cursor.getString(cursor.getColumnIndexOrThrow("poster_path")),
        cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path")),
        cursor.getString(cursor.getColumnIndexOrThrow("overview")),
        cursor.getString(cursor.getColumnIndexOrThrow("release_date"))
    )

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

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}