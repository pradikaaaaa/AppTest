package com.example.apptest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.apptest.R
import com.example.apptest.adapter.RecipeAdapter
import com.example.apptest.viewmodels.RecipeState
import com.example.apptest.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var recipesViewModel : RecipeViewModel
    private lateinit var rv_recipe : ShimmerRecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.apply {
            rv_recipe = findViewById(R.id.rv_list_recipe)
        }

        recipesViewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
        setupRecyler()
        recipesViewModel.getAllRecipes()
        recipesViewModel.getRecipes().observe(viewLifecycleOwner, Observer {
            rv_recipe.adapter?.let { adapter ->
                if (adapter is RecipeAdapter){
                    adapter.setRecipes(it)
                }
            }
        })

        recipesViewModel.getStates().observer(viewLifecycleOwner, Observer {
            handleUIState(it)
        })

        return root
    }

    private fun setupRecyler(){
        rv_recipe.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = RecipeAdapter(mutableListOf(),this@HomeFragment.requireContext())
        }
        rv_recipe.showShimmerAdapter()
    }

    private fun handleUIState(it : RecipeState){
        when(it){
            is RecipeState.Error -> {
                Toast.makeText(this@HomeFragment.requireContext(),it.err,Toast.LENGTH_SHORT).show()
            }
            is RecipeState.IsSuccess -> {
                rv_recipe.hideShimmerAdapter()
            }
        }
    }


}