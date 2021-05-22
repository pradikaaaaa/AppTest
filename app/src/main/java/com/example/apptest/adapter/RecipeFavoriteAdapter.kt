package com.example.apptest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.fragment.FavoritFragment
import com.example.apptest.helper.DatabaseHelper
import com.example.apptest.models.ModelFavorit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_recipe.view.*
import kotlinx.android.synthetic.main.list_item_recipe.view.imgRecipe
import kotlinx.android.synthetic.main.list_item_recipe.view.txt_nama_resep
import kotlinx.android.synthetic.main.list_item_recipe_favorit.view.*

class RecipeFavoriteAdapter : RecyclerView.Adapter<RecipeFavoriteAdapter.ViewHolder>() {

    private var items : List<ModelFavorit> = ArrayList()
    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe_favorit, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(favorit: ModelFavorit, context: Context){
            var dbHelper = DatabaseHelper(context)


            itemView.txt_nama_resep.text = favorit.judul
            Picasso.get()
                .load(favorit.img_url)
                .into(itemView.imgRecipe)

            itemView.deleteFromFavorit.setOnClickListener {
                try{
                    dbHelper.deleteData(favorit.id.toString())
                    Toast.makeText(context,"Berhasil menghapus dari favorit", Toast.LENGTH_SHORT).show()
                }catch (e : Exception){
                    e.printStackTrace()
                    Toast.makeText(context,e.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun setFavorit(favoritList : List<ModelFavorit>, context: Context){
        items = favoritList
        this.context = context
    }
}