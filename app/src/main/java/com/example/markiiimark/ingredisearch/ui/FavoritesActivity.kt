package com.example.markiiimark.ingredisearch.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.markiiimark.ingredisearch.R
import com.example.markiiimark.ingredisearch.api.RecipeRepository
import com.example.markiiimark.ingredisearch.api.RecipeRepositoryImpl
import com.example.markiiimark.ingredisearch.model.Recipe
import com.example.markiiimark.ingredisearch.ui.adapter.RecipeAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_noresults.*

class FavoritesActivity : AppCompatActivity() {

    private val repository by lazy {  RecipeRepositoryImpl.getRepository(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listOf(loadingContainer, errorContainer, list, noresultsContainer).forEach { v -> v.visibility = View.GONE }
        noresultsTitle.text = getString(R.string.nofavorties)

        val favoriteRecipes = repository.getFavoriteRecipes()
        if (favoriteRecipes.isEmpty()) {
            showEmptyRecipes()
        } else {
            showRecipes(favoriteRecipes)
        }
    }

    private fun showEmptyRecipes() {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.VISIBLE
    }

    private fun showRecipes(recipes: List<Recipe>) {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.GONE

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = RecipeAdapter(recipes, object: RecipeAdapter.Listener {
            override fun onClickItem(item: Recipe) {
                startActivity(recipeIntent(item.sourceUrl))
            }

            override fun onAddFavorite(item: Recipe) {
                item.isFavorited = true
                repository.addFavorite(item)
                list.adapter.notifyItemChanged(recipes.indexOf(item))
            }

            override fun onRemoveFavorite(item: Recipe) {
                repository.removeFavorite(item)
                (list.adapter as RecipeAdapter).removeItem(item)
                list.adapter.notifyItemRemoved(recipes.indexOf(item))
                if (list.adapter.itemCount == 0) showEmptyRecipes()
            }
        })
    }
}
