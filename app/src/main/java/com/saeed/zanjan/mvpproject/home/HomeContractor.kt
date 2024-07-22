package com.saeed.zanjan.mvpproject.home

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.Banners
import com.saeed.zanjan.mvpproject.data.News

interface HomeContractor {

    interface View:BaseView{

        fun showNews(showNewsList:List<News>)
        fun showBanners(showBannersList:List<News>)


    }
    interface Presenter:BasePresenter<View>{
        fun getNews()
        fun getBanners()


    }
}