package com.saeed.zanjan.mvpproject.data

import com.google.gson.annotations.SerializedName

data class Category(

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
