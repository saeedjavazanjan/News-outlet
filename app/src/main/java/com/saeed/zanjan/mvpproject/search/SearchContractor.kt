package com.saeed.zanjan.mvpproject.search

import android.icu.text.CaseMap
import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.News

class SearchContractor {

    interface View:BaseView{
        fun showSearchedData(news:List<News>)

    }
    interface Presenter:BasePresenter<View>{

        fun  getSearchedData(title:CharSequence)

    }
}