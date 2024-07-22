package com.saeed.zanjan.mvpproject.base

import android.view.View
import java.util.*

interface BasePresenter<T:BaseView> {

    fun onAttachView(view: T)
    fun onDetachView()

}