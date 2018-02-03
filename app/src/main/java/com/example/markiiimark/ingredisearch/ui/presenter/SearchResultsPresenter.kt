package com.example.markiiimark.ingredisearch.ui.presenter

import com.example.markiiimark.ingredisearch.api.RecipeRepository
import com.example.markiiimark.ingredisearch.model.Recipe

class SearchResultsPresenter(val repository: RecipeRepository,
                             private var recipes:List<Recipe>? = null) : BasePresenter<SearchResultsPresenter.View>() {

    fun search(query: String) {
        view?.showLoading()
        repository.getRecipes(query, object: RecipeRepository.RepositoryCallback<List<Recipe>> {
            override fun onSuccess(recipes: List<Recipe>?) {
                this@SearchResultsPresenter.recipes = recipes
                if (recipes != null && recipes.isNotEmpty()) {
                    view?.showRecipes(recipes)
                } else {
                    view?.showEmptyRecipes()
                }
            }

            override fun onError() {
                view?.showError()
            }
        })
    }

    fun addFavorite(recipe: Recipe) {
        recipe.isFavorited = true
        repository.addFavorite(recipe)
        val recipeIndex= recipes?.indexOf(recipe)
        if (recipeIndex != null) {
            view?.refreshFavoritesStatus(recipeIndex)
        }
    }

    fun removeFavorite(recipe: Recipe) {
        repository.removeFavorite(recipe)
        recipe.isFavorited = false
        val recipeIndex = recipes?.indexOf(recipe)
        if (recipeIndex != null) view?.refreshFavoritesStatus(recipeIndex)
    }


    interface View {
        fun showLoading()
        fun showRecipes(recipes: List<Recipe>)
        fun showEmptyRecipes()
        fun showError()
        fun refreshFavoritesStatus(recipeIndex: Int)
    }



}