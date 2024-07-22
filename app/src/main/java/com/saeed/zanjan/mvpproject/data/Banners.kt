package com.saeed.zanjan.mvpproject.data

import com.google.gson.annotations.SerializedName

data class Banners(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("video")
	val video: String? = null,

	@field:SerializedName("writer")
	val writer: String? = null,

	@field:SerializedName("groupingname")
	val groupingname: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("image_writer")
	val imageWriter: String? = null
)
