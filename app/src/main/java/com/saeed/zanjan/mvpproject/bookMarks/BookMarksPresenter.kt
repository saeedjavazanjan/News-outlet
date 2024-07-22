package com.saeed.zanjan.mvpproject.bookMarks

import android.view.View
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookMarksPresenter(dataSource: DataSource):BookMarksContractor.Presenter {
    var view: BookMarksContractor.View?=null
    var datas:DataSource?=null
    var disposable: CompositeDisposable?= CompositeDisposable()

    init {
        this.datas=dataSource

    }


    override fun getBookMarks() {
        view!!.showProgressBar(true)
        datas!!.getBookMarks().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<News>>{
                override fun onSubscribe(d: Disposable) {
                    disposable!!.add(d)
                }

                override fun onSuccess(t: List<News>) {
                    view!!.showProgressBar(false)
                    view!!.showBookMArks(t)
                    if(t.isEmpty()){
                        view!!.showEmptyLayout(true)

                    }else{
                        view!!.showEmptyLayout(false)

                    }
                }

                override fun onError(e: Throwable) {
                    view!!.showError("خطا در ارتباط")
                }

            })

    }

    override fun onAttachView(view: BookMarksContractor.View) {
        this.view=view
        getBookMarks()
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!=null || disposable!!.size()>0){

            disposable!!.clear()
        }    }
}