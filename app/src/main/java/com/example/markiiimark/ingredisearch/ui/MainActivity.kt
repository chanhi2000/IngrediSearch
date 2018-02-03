package com.example.markiiimark.ingredisearch.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.markiiimark.ingredisearch.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun searchButtonClicked(v: View) = startActivity(Intent(this@MainActivity, SearchActivity::class.java))
    fun favButtonClicked(v:View) = startActivity(Intent(this@MainActivity, FavoritesActivity::class.java))
}