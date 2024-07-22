package com.saeed.zanjan.mvpproject.journals

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.data.News

interface JournalsContractor  {

    interface View:BaseView{

        fun showJournals(showJournalsList:List<Journal>)

    }

    interface Presenter:BasePresenter<View>{
        fun getJournals()

    }


}