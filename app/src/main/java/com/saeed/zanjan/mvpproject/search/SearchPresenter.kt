package com.saeed.zanjan.mvpproject.search

import android.view.View
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(dataSource: DataSource):SearchContractor.Presenter {
    var view:SearchContractor.View?=null
    var data:DataSource?=null
    var disposable:CompositeDisposable= CompositeDisposable()
    var title:CharSequence?=null


    init {
        this.data=dataSource
    }
    override fun getSearchedData(title: CharSequence) {
        view!!.showProgressBar(true)
        data!!.getSearchedData(title).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<News>>{
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: List<News>) {
                   view!!.showSearchedData(t)
                    view!!.showProgressBar(false)

                }

                override fun onError(e: Throwable) {
                    view!!.showError("خطا در ارتباط")
                   view!!.showProgressBar(false)

                }

            })
    }

    override fun onAttachView(view: SearchContractor.View) {
        this.view=view

    }


    override fun onDetachView() {
        this.view=null
        if(disposable.size()>0 ){

            disposable.clear()
        }

    }
}