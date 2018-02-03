package com.example.markiiimark.ingredisearch.ui

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import com.example.markiiimark.ingredisearch.R
import com.example.markiiimark.ingredisearch.ui.presenter.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : ChildActivity(), SearchPresenter.View {

    private val presenter: SearchPresenter = SearchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter.attachView(this)

    }

    fun searchButtonClicked(v:View) {
        val query = ingredients.text.toString().trim()
        presenter.search(query)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showQueryRequiredMessage() {
        val view = this.currentFocus // hid keyboard
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        Snackbar.make(searchButton, getString(R.string.search_query_required), Snackbar.LENGTH_LONG).show()

    }

    override fun showSearchResults(query: String) {
        startActivity(searchResultsIntent(query))
    }
}
