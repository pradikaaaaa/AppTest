package com.example.apptest.webservices

import com.example.apptest.utils.RecipesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    fun getRecipes() : Call<RecipesResponse>
}