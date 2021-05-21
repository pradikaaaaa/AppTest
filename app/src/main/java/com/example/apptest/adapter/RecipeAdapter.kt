package com.example.apptest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.models.ModelRecipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_recipe.view.*

class RecipeAdapter(private var recipes : MutableList<ModelRecipe>, private var context: Context) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    fun setRecipes(r : List<ModelRecipe>){
        recipes.clear()
        recipes.addAll(r)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        fun bind(recipe : ModelRecipe, context: Context){
            itemView.txt_nama_resep.text = recipe.title
            Picasso.get()
                .load(recipe.thumb)
                .into(itemView.imgRecipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position],context)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}