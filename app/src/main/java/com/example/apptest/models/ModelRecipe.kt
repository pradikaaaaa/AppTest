package com.example.apptest.models

import com.google.gson.annotations.SerializedName

data class ModelRecipe(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null
)
