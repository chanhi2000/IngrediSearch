package com.example.markiiimark.ingredisearch.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.markiiimark.ingredisearch.R
import com.example.markiiimark.ingredisearch.model.Recipe
import kotlinx.android.synthetic.main.listitem_recipe.view.*

class RecipeAdapter(private var items: List<Recipe>,
                    private val listener: Listener) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val retView = LayoutInflater.from(parent.context).inflate(R.layout.listitem_recipe, parent, false)
        return ViewHolder(retView)
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) =
            holder.bindRecipe(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun removeItem(item: Recipe) {
        items -= item
    }

    inner class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {
        fun bindRecipe(item: Recipe, listener: Listener) = with(itemView) {
            Glide.with(context).load(item.imageUrl).into(imageView)
            title.text = item.title

            favButton.setImageResource( if (item.isFavorited) R.drawable.ic_favorite_24dp else R.drawable.ic_favorite_border_24dp)

            setOnClickListener {
                listener.onClickItem(item)
            }

            favButton.setOnClickListener {
                 if(item.isFavorited) listener.onRemoveFavorite(item) else listener.onAddFavorite(item)
            }

        }
    }
    interface Listener {
        fun onClickItem(item: Recipe)
        fun onAddFavorite(item: Recipe)
        fun onRemoveFavorite(item: Recipe)
    }
}