package com.example.markiiimark.ingredisearch.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.markiiimark.ingredisearch.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : ChildActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    fun searchButtonClicked(v:View) {
        val query = ingredients.text.toString().trim()
        if (query.isBlank()) {
            Snackbar.make(searchButton, getString(R.string.search_query_required), Snackbar.LENGTH_LONG).show()
        } else {
            startActivity(searchResultsIntent(query))
        }
    }
}
