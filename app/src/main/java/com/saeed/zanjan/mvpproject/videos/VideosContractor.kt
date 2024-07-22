package com.saeed.zanjan.mvpproject.videos

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.Videos

class VideosContractor {

    interface View:BaseView{
        fun showVideos(videoList:List<Videos>)

    }

    interface Presenter:BasePresenter<View>{
        fun getVideos()

    }
}