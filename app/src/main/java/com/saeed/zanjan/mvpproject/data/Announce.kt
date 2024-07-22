package com.saeed.zanjan.mvpproject.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Announce(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
): Serializable {
	var isBookMarked:Boolean?= false
}
