package com.example.apptest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.helper.DatabaseHelper
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
            var dbHelper = DatabaseHelper(context)

            itemView.txt_nama_resep.text = recipe.title
            Picasso.get()
                .load(recipe.thumb)
                .into(itemView.imgRecipe)

            itemView.addToFavorit.setOnClickListener {
                val res = dbHelper.cekData(recipe.key.toString())

                if(res.count == 0 ) {
                    try {
                        dbHelper.insertData(recipe.title.toString(), recipe.thumb.toString(), recipe.key.toString())
                        Toast.makeText(context, "Berhasil menambahkan ke favorit", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context,"Sudah dalam favorit", Toast.LENGTH_SHORT).show()
                }
            }

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