package com.example.apptest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptest.models.ModelRecipe
import com.example.apptest.utils.RecipesResponse
import com.example.apptest.utils.SingleLiveEvent
import com.example.apptest.webservices.ApiClient
import retrofit2.Call
import retrofit2.Response

class RecipeViewModel : ViewModel(){
    private var recipes = MutableLiveData<List<ModelRecipe>>()
    private var state : SingleLiveEvent<RecipeState> = SingleLiveEvent()
    private var api = ApiClient.instance()

    fun getAllRecipes(){
        state.value = RecipeState.IsLoading(true)
        api.getRecipes().enqueue(object : retrofit2.Callback<RecipesResponse>{
            override fun onResponse(call: Call<RecipesResponse>, response: Response<RecipesResponse>) {
                if (response.isSuccessful){
                    val body = response.body() as RecipesResponse
                    val r = body.results
                    recipes.postValue(r)
                    state.value = RecipeState.IsSuccess("Berhasil")
                }else{
                    state.value = RecipeState.Error("Terjadi kesalahan.")
                }

                state.value = RecipeState.IsLoading(false)
            }

            override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                state.value = RecipeState.Error(t.message.toString())
            }

        })
    }

    fun getRecipes() = recipes
    fun getStates() = state
}

sealed class RecipeState{
    data class ShowToast(var message : String) : RecipeState()
    data class IsLoading(var state : Boolean = false) : RecipeState()
    data class Error(var err : String) : RecipeState()
    data class IsSuccess(var success : String?) : RecipeState()
}