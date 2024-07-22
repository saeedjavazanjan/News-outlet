package com.saeed.zanjan.mvpproject.announcement

import androidx.lifecycle.MutableLiveData
import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.Announce

interface AnnouncementContractor {

    interface View:BaseView{
        fun showAnnounce(showAnnounce: MutableLiveData<List<Announce>>)
    }
    interface Presenter:BasePresenter<View>{
        fun getAnnounce()


    }

}