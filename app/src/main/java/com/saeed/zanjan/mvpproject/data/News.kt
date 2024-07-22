package com.saeed.zanjan.mvpproject.data

import android.os.Parcel
import android.os.Parcelable
import android.text.ParcelableSpan
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "book_marks")
data class News(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,
	@PrimaryKey
	@field:SerializedName("id")
	val id: String= "0",

	@field:SerializedName("video")
	val video: String? = null,

	@field:SerializedName("writer")
	val writer: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("grouping")
	val grouping: String? = null,

	@field:SerializedName("image_writer")
	val imageWriter: String? = null

) : Serializable{
	var isBookMarked:Boolean?= false
}