package com.saeed.zanjan.mvpproject.bookMarks

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.News

class BookMarksContractor {

    interface View:BaseView{
        fun showBookMArks(bookMarks:List<News>)
        fun showEmptyLayout(isEmpty:Boolean)

    }
    interface Presenter:BasePresenter<View>{
        fun getBookMarks()
    }
}