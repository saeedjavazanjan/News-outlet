package com.saeed.zanjan.mvpproject.data

import com.google.gson.annotations.SerializedName

data class Videos(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("videolink")
	val videolink: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
