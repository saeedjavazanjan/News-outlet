package com.saeed.zanjan.mvpproject.welfare

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.News

interface WelfareContractor {

    interface View:BaseView{
        fun showWelfareNews(showWelfareNews:List<News>)
    }

    interface Presenter:BasePresenter<View>{

        fun getWelfareNews()
    }
}