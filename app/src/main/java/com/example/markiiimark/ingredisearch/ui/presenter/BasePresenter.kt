package com.example.markiiimark.ingredisearch.ui.presenter

abstract class BasePresenter<V> {
    protected var view: V? = null
    fun attachView(v: V) {  this.view = v }
    fun detachView() { this.view = null }
}