package com.example.apptest.utils

import com.example.apptest.models.ModelRecipe
import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("method") val method: String,
    @SerializedName("status") val status: Boolean,
    @SerializedName("results") val results : List<ModelRecipe>
)