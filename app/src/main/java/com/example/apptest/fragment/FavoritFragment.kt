package com.example.apptest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.apptest.R
import com.example.apptest.adapter.RecipeFavoriteAdapter
import com.example.apptest.helper.DatabaseHelper
import com.example.apptest.models.ModelFavorit
import kotlinx.android.synthetic.main.fragment_home.*

class FavoritFragment : Fragment() {

    private lateinit var rv_recipe : ShimmerRecyclerView
    private lateinit var txt_kosong : TextView

    companion object{
        lateinit var mContext : FavoritFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorit, container, false)

        root.apply {
            rv_recipe = findViewById(R.id.rv_list_recipe_favorit)
            txt_kosong = findViewById(R.id.txt_kosong)
            mContext = this@FavoritFragment
        }

        setupRecyler()
        handleView()

        return root
    }

    private fun setupRecyler(){
        rv_recipe.apply {
           layoutManager = LinearLayoutManager(this@FavoritFragment.requireContext())
            adapter = RecipeFavoriteAdapter()
        }
//        rv_recipe.showShimmerAdapter()
    }

    override fun onResume() {
        super.onResume()
        handleView()
    }

    fun handleView(){
        var dbHelper = DatabaseHelper(this@FavoritFragment.requireContext())
        var data = arrayListOf<ModelFavorit>()
        val res = dbHelper.allData()

        if (res.count == 0){
            txt_kosong.isVisible = true
        }

        if (res.moveToFirst()){
            do {
                data.add(
                    ModelFavorit(
                        res.getInt(res.getColumnIndex("id")),
                        res.getString(res.getColumnIndex("judul")),
                        res.getString(res.getColumnIndex("img_url")),
                        res.getString(res.getColumnIndex("slug"))
                    )
                )
            }while (res.moveToNext())
        }

        rv_recipe.adapter?.let { adapter ->
            if(adapter is RecipeFavoriteAdapter){
                adapter.setFavorit(data,this@FavoritFragment.requireContext())
            }
        }
    }



}