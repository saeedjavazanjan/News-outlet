package com.saeed.zanjan.mvpproject.base

import android.content.Context

interface BaseView {
    fun showProgressBar(showOrNot:Boolean)
    fun context():Context
    fun showError(text:String)
}